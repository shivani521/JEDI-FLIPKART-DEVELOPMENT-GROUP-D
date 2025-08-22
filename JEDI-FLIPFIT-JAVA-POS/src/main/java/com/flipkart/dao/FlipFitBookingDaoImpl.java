package com.flipkart.dao;

import com.flipkart.bean.Booking;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Booking DAO (read-only helpers for owner scope).
 * Expected Booking columns:
 *  - bookingId, customerId, gymId, slotId, bookingDate, status
 */
public class FlipFitBookingDaoImpl {

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT bookingId, customerId, gymId, slotId, bookingDate, status FROM Booking";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Booking b = new Booking();
                b.setBookingId(rs.getInt("bookingId"));
                b.setCustomerId(rs.getInt("customerId"));
                b.setGymId(rs.getInt("gymId"));
                b.setSlotId(rs.getInt("slotId"));
                b.setBookingDate(rs.getDate("bookingDate"));
                b.setStatus(rs.getString("status"));
                bookings.add(b);
            }
        }
        return bookings;
    }

    /**
     * Get bookings for all gyms owned by ownerId.
     * This joins Booking -> Gym to filter by ownerId.
     */
    public List<Booking> getBookingsByOwner(int ownerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bookingId, b.customerId, b.gymId, b.slotId, b.bookingDate, b.status " +
                "FROM Booking b JOIN Gym g ON b.gymId = g.gymId WHERE g.ownerId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking b = new Booking();
                    b.setBookingId(rs.getInt("bookingId"));
                    b.setCustomerId(rs.getInt("customerId"));
                    b.setGymId(rs.getInt("gymId"));
                    b.setSlotId(rs.getInt("slotId"));
                    b.setBookingDate(rs.getDate("bookingDate"));
                    b.setStatus(rs.getString("status"));
                    bookings.add(b);
                }
            }
        }
        return bookings;
    }
}