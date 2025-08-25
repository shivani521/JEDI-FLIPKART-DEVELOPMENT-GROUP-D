package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `FlipFitBookingDaoImpl` class provides the data access layer for booking-related operations.
 * It handles creating, retrieving, and canceling bookings by interacting with the database.
 * This class ensures transactional integrity for critical operations like booking and cancellation.
 */
public class FlipFitBookingDaoImpl {

    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();

    /**
     * Retrieves a list of all bookings for a given customer ID.
     *
     * @param customerId The unique ID of the customer.
     * @return A list of `Booking` objects associated with the customer.
     * @throws SQLException if a database access error occurs.
     */
    public List<Booking> getBookingsByCustomer(int customerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT bookingId, customerId, gymId, slotId, bookingDate, bookingStatus, createdAt FROM Booking WHERE customerId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setBookingId(rs.getInt("bookingId"));
                    b.setCustomerId(rs.getInt("customerId"));
                    b.setGymId(rs.getInt("gymId"));
                    b.setSlotId(rs.getInt("slotId"));
                    b.setBookingDate(rs.getDate("bookingDate"));
                    b.setBookingStatus(rs.getString("bookingStatus"));
                    b.setCreatedAt(rs.getTimestamp("createdAt"));
                    bookings.add(b);
                }
            }
        }
        return bookings;
    }

    /**
     * Creates a new booking in a transactional manner. It first locks the slot,
     * decrements the number of available seats, and then inserts the new booking record.
     *
     * @param customerId The ID of the customer making the booking.
     * @param gymId The ID of the gym.
     * @param slotId The ID of the slot to be booked.
     * @param slotDate The date of the slot.
     * @param slotStartTime The start time of the slot.
     * @return The generated booking ID on success; returns -1 if there are no seats available or an error occurs.
     * @throws SQLException if a database access error occurs.
     */
    public int createBooking(int customerId, int gymId, int slotId, Date slotDate, Time slotStartTime) throws SQLException {
        Connection con = null;
        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            // lock and read seats
            Integer seats = slotDao.getSeatsForUpdate(con, slotId);
            if (seats == null) {
                con.rollback();
                return -1;
            }
            if (seats <= 0) {
                con.rollback();
                return -1; // no seats
            }

            // decrement seats
            int updated = slotDao.updateSeats(con, slotId, seats - 1);
            if (updated != 1) {
                con.rollback();
                return -1;
            }

            // insert booking
            String insertSql = "INSERT INTO Booking (customerId, gymId, slotId, bookingDate, bookingStatus, createdAt) " +
                    "VALUES (?, ?, ?, ?, 'ACTIVE', CURRENT_TIMESTAMP)";
            try (PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, customerId);
                ps.setInt(2, gymId);
                ps.setInt(3, slotId);
                ps.setDate(4, slotDate);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int bookingId = rs.getInt(1);
                        con.commit();
                        return bookingId;
                    } else {
                        con.rollback();
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw e;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
            }
        }
    }

    /**
     * Cancels an existing booking in a transactional manner. It first marks the booking
     * as "CANCELLED" and then increments the number of available seats in the corresponding slot.
     *
     * @param bookingId The unique ID of the booking to be cancelled.
     * @return `true` if the booking was successfully cancelled; `false` if the booking
     * is not found or an error occurs.
     * @throws SQLException if a database access error occurs.
     */
    public boolean cancelBooking(int bookingId) throws SQLException {
        Connection con = null;
        try {
            con = DbConnection.getConnection();
            con.setAutoCommit(false);

            // fetch booking row FOR UPDATE to lock it
            String sel = "SELECT bookingId, slotId, bookingStatus FROM Booking WHERE bookingId = ? FOR UPDATE";
            int slotId;
            String bookingStatus;
            try (PreparedStatement ps = con.prepareStatement(sel)) {
                ps.setInt(1, bookingId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return false; // not found
                    }
                    slotId = rs.getInt("slotId");
                    bookingStatus = rs.getString("bookingStatus");
                }
            }

            if (!"ACTIVE".equalsIgnoreCase(bookingStatus)) {
                con.rollback();
                return false; // already cancelled or not active
            }

            // mark booking cancelled
            String updBooking = "UPDATE Booking SET bookingStatus = 'CANCELLED' WHERE bookingId = ?";
            try (PreparedStatement ps = con.prepareStatement(updBooking)) {
                ps.setInt(1, bookingId);
                ps.executeUpdate();
            }

            // increment seats
            Integer seats = slotDao.getSeatsForUpdate(con, slotId);
            if (seats == null) {
                con.rollback();
                return false;
            }
            int updated = slotDao.updateSeats(con, slotId, seats + 1);
            if (updated != 1) {
                con.rollback();
                return false;
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ignored) {}
            throw e;
        } finally {
            if (con != null) {
                try { con.setAutoCommit(true); con.close(); } catch (SQLException ignored) {}
            }
        }
    }
}