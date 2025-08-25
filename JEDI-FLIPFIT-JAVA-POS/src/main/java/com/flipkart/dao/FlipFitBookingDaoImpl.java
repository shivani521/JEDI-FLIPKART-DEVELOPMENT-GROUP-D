package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Booking DAO: create/cancel and list bookings.
 * Expects a "Booking" table with columns:
 * bookingId (PK, auto-inc), customerId, bookingStatus, paymentId, createdAt, gymId, slotId, bookingDate, ...
 *
 * Important: the SQL explicitly lists target columns (including bookingStatus) so table column order doesn't matter.
 */
public class FlipFitBookingDaoImpl {

    private FlipFitSlotDaoImpl slotDao = new FlipFitSlotDaoImpl();

    /**
     * Get bookings for a customer.
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
     * Create booking with transaction: lock slot, decrement seats, insert booking.
     * Returns generated bookingId (>0) on success, -1 on error / no seats.
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

            // insert booking.
            // Note: explicitly list columns (bookingStatus included). createdAt is set to CURRENT_TIMESTAMP by DB.
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
     * Cancel booking transactionally: mark booking cancelled and increment slot seats.
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