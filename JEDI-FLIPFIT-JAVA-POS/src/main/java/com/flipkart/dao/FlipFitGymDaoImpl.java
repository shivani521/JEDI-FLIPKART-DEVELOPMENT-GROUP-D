package com.flipkart.dao;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Gym table.
 *
 * Table columns expected:
 *  - gymId (INT AUTO_INCREMENT PRIMARY KEY)
 *  - gymName (VARCHAR)
 *  - ownerId (INT)
 *  - gymAddress (VARCHAR)
 *  - numberOfSlots (INT)
 *  - cost (INT)
 *  - gymStatus (INT)    -- e.g. 0 = PENDING, 1 = ACTIVE, 2 = REJECTED
 *  - ownerGstNumber (VARCHAR)
 */
public class FlipFitGymDaoImpl {

    public int createGym(FlipFitGym gym, int ownerId) throws SQLException {
        String sql = "INSERT INTO FlipFitGym (gymName, ownerId, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, gym.getGymName());
            ps.setInt(2, ownerId);
            ps.setString(3, gym.getGymAddress());
            ps.setInt(4, gym.getNumberOfSlots());
            ps.setInt(5, gym.getCost());
            ps.setInt(6, gym.getGymStatus());
            ps.setString(7, gym.getOwnerGstNumber());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert gym");
    }

    public int deleteGym(int gymId) throws SQLException {
        String sql = "DELETE FROM Gym WHERE gymId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gymId);
            return ps.executeUpdate();
        }
    }

    public int updateGym(FlipFitGym gym) throws SQLException {
        String sql = "UPDATE Gym SET gymName = ?, gymAddress = ?, numberOfSlots = ?, cost = ?, gymStatus = ?, ownerGstNumber = ? WHERE gymId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gym.getGymName());
            ps.setString(2, gym.getGymAddress());
            ps.setInt(3, gym.getNumberOfSlots());
            ps.setInt(4, gym.getCost());
            ps.setInt(5, gym.getGymStatus());
            ps.setString(6, gym.getOwnerGstNumber());
            ps.setInt(7, gym.getGymId());
            return ps.executeUpdate();
        }
    }

    public FlipFitGym getGymById(int gymId) throws SQLException {
        String sql = "SELECT gymId, gymName, ownerId, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber FROM Gym WHERE gymId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gymId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FlipFitGym g = new FlipFitGym();
                    g.setGymId(rs.getInt("gymId"));
                    g.setGymName(rs.getString("gymName"));
                    g.setOwnerId(rs.getInt("ownerId"));
                    g.setGymAddress(rs.getString("gymAddress"));
                    g.setNumberOfSlots(rs.getInt("numberOfSlots"));
                    g.setCost(rs.getInt("cost"));
                    g.setGymStatus(rs.getInt("gymStatus"));
                    g.setOwnerGstNumber(rs.getString("ownerGstNumber"));
                    return g;
                }
            }
        }
        return null;
    }

    public List<FlipFitGym> getPendingGyms() throws SQLException {
        List<FlipFitGym> gyms = new ArrayList<>();
        String sql = "SELECT gymId, gymName, ownerId, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber FROM FlipFitGym WHERE gymStatus = 0";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                FlipFitGym g = new FlipFitGym();
                g.setGymId(rs.getInt("gymId"));
                g.setGymName(rs.getString("gymName"));
                g.setOwnerId(rs.getInt("ownerId"));
                g.setGymAddress(rs.getString("gymAddress"));
                g.setNumberOfSlots(rs.getInt("numberOfSlots"));
                g.setCost(rs.getInt("cost"));
                g.setGymStatus(rs.getInt("gymStatus"));
                g.setOwnerGstNumber(rs.getString("ownerGstNumber"));
                gyms.add(g);
            }
        }
        return gyms;
    }

    public List<FlipFitGym> getGymsByOwner(int ownerId) throws SQLException {
        List<FlipFitGym> gyms = new ArrayList<>();
        String sql = "SELECT gymId, gymName, ownerId, gymAddress, numberOfSlots, cost, gymStatus, ownerGstNumber FROM FlipFitGym WHERE ownerId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FlipFitGym g = new FlipFitGym();
                    g.setGymId(rs.getInt("gymId"));
                    g.setGymName(rs.getString("gymName"));
                    g.setOwnerId(rs.getInt("ownerId"));
                    g.setGymAddress(rs.getString("gymAddress"));
                    g.setNumberOfSlots(rs.getInt("numberOfSlots"));
                    g.setCost(rs.getInt("cost"));
                    g.setGymStatus(rs.getInt("gymStatus"));
                    g.setOwnerGstNumber(rs.getString("ownerGstNumber"));
                    gyms.add(g);
                }
            }
        }
        return gyms;
    }
}