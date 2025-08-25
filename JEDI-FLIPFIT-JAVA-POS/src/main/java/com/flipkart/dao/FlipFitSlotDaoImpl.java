package com.flipkart.dao;

import com.flipkart.bean.Slot;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `FlipFitSlotDaoImpl` class provides the data access layer for slot-related operations.
 * It handles all database interactions for slot data, including creating, retrieving,
 * updating, and deleting slot records.
 */
public class FlipFitSlotDaoImpl {

    /**
     * Adds a new slot to the database.
     *
     * @param slot The `Slot` object containing the slot's details.
     * @return The generated `slotId` of the newly added slot.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Deletes a slot record from the database using its unique ID.
     *
     * @param slotId The unique ID of the slot to be deleted.
     * @return The number of rows affected (should be 1 if successful).
     * @throws SQLException if a database access error occurs.
     */
    public int deleteSlot(int slotId) throws SQLException {
        String sql = "DELETE FROM Slot WHERE slotId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, slotId);
            return ps.executeUpdate();
        }
    }

    /**
     * Updates an existing slot record in the database.
     *
     * @param slot The `Slot` object with the updated details.
     * @return The number of rows affected (should be 1 if successful).
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a `Slot` object from the database using its unique ID.
     *
     * @param slotId The unique ID of the slot to retrieve.
     * @return The `Slot` object if a matching record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a list of all slots with a pending status.
     *
     * @return A list of `Slot` objects that are pending approval.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a list of all slots for a specific gym on a given date.
     *
     * @param gymId The unique ID of the gym.
     * @param date The date for which to retrieve slots.
     * @return A list of `Slot` objects for the specified gym and date.
     * @throws SQLException if a database access error occurs.
     */
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
     * Locks a slot row for a transaction and returns the number of available seats.
     * This is an internal helper method used for transactional booking to prevent race conditions.
     *
     * @param con The database connection for the ongoing transaction.
     * @param slotId The unique ID of the slot to lock.
     * @return The number of available seats if the slot is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
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
     * Updates the number of available seats for a slot within a transaction.
     *
     * @param con The database connection for the ongoing transaction.
     * @param slotId The unique ID of the slot to update.
     * @param newSeats The new number of available seats.
     * @return The number of rows affected.
     * @throws SQLException if a database access error occurs.
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