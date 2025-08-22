package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Slot DAO
 * Expected Slot table columns:
 *  - slotId (INT AUTO_INCREMENT PRIMARY KEY)
 *  - status (VARCHAR)    -- e.g. 'PENDING','ACTIVE'
 *  - gymId (INT)
 *  - startTime (TIME)
 *  - endTime (TIME)
 *  - seatsAvailable (INT)
 *  - price (DOUBLE)
 *  - slotDate (DATE)
 *  - totalSeats (INT)
 */
public class FlipFitSlotDaoImpl {

    public int addSlot(Slot slot) throws SQLException {
        String sql = "INSERT INTO Slot (status, gymId, startTime, endTime, seatsAvailable, price, slotDate, totalSeats) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, slot.getStatus());
            ps.setInt(2, slot.getGymId());
            ps.setTime(3, slot.getStartTime());
            ps.setTime(4, slot.getEndTime());
            ps.setInt(5, slot.getSeatsAvailable());
            ps.setDouble(6, slot.getPrice());
            ps.setDate(7, slot.getSlotDate());
            ps.setInt(8, slot.getTotalSeats());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert slot");
    }

    public int deleteSlot(int slotId) throws SQLException {
        String sql = "DELETE FROM Slot WHERE slotId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, slotId);
            return ps.executeUpdate();
        }
    }

    public int updateSlot(Slot slot) throws SQLException {
        String sql = "UPDATE Slot SET status = ?, gymId = ?, startTime = ?, endTime = ?, seatsAvailable = ?, price = ?, slotDate = ?, totalSeats = ? WHERE slotId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, slot.getStatus());
            ps.setInt(2, slot.getGymId());
            ps.setTime(3, slot.getStartTime());
            ps.setTime(4, slot.getEndTime());
            ps.setInt(5, slot.getSeatsAvailable());
            ps.setDouble(6, slot.getPrice());
            ps.setDate(7, slot.getSlotDate());
            ps.setInt(8, slot.getTotalSeats());
            ps.setInt(9, slot.getSlotId());
            return ps.executeUpdate();
        }
    }

    public Slot getSlotById(int slotId) throws SQLException {
        String sql = "SELECT slotId, status, gymId, startTime, endTime, seatsAvailable, price, slotDate, totalSeats FROM Slot WHERE slotId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, slotId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Slot(
                            rs.getString("status"),
                            rs.getInt("slotId"),
                            rs.getInt("gymId"),
                            rs.getTime("startTime"),
                            rs.getTime("endTime"),
                            rs.getInt("seatsAvailable"),
                            rs.getDouble("price"),
                            rs.getDate("slotDate"),
                            rs.getInt("totalSeats")
                    );
                }
            }
        }
        return null;
    }

    public List<Slot> getPendingSlots() throws SQLException {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT slotId, status, gymId, startTime, endTime, seatsAvailable, price, slotDate, totalSeats FROM Slot WHERE status = 'PENDING'";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Slot s = new Slot(
                        rs.getString("status"),
                        rs.getInt("slotId"),
                        rs.getInt("gymId"),
                        rs.getTime("startTime"),
                        rs.getTime("endTime"),
                        rs.getInt("seatsAvailable"),
                        rs.getDouble("price"),
                        rs.getDate("slotDate"),
                        rs.getInt("totalSeats")
                );
                slots.add(s);
            }
        }
        return slots;
    }

    public List<Slot> getSlotsByGym(int gymId, Date date) throws SQLException {
        List<Slot> slots = new ArrayList<>();
        String sql = "SELECT slotId, status, gymId, startTime, endTime, seatsAvailable, price, slotDate, totalSeats FROM Slot WHERE gymId = ? AND slotDate = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gymId);
            ps.setDate(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Slot s = new Slot(
                            rs.getString("status"),
                            rs.getInt("slotId"),
                            rs.getInt("gymId"),
                            rs.getTime("startTime"),
                            rs.getTime("endTime"),
                            rs.getInt("seatsAvailable"),
                            rs.getDouble("price"),
                            rs.getDate("slotDate"),
                            rs.getInt("totalSeats")
                    );
                    slots.add(s);
                }
            }
        }
        return slots;
    }

    /**
     * Helper used by booking transaction: lock row and return seatsAvailable (FOR UPDATE).
     * Caller must pass an open Connection and manage commit/rollback.
     */
    public Integer getSeatsForUpdate(Connection con, int slotId) throws SQLException {
        String sql = "SELECT seatsAvailable FROM Slot WHERE slotId = ? FOR UPDATE";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, slotId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("seatsAvailable");
            }
        }
        return null;
    }

    /**
     * Update seatsAvailable for a slot (used inside transaction).
     */
    public int updateSeats(Connection con, int slotId, int newSeats) throws SQLException {
        String sql = "UPDATE Slot SET seatsAvailable = ? WHERE slotId = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, newSeats);
            ps.setInt(2, slotId);
            return ps.executeUpdate();
        }
    }
}