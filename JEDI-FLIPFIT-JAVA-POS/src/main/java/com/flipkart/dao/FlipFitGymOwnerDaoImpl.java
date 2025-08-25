package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerDaoImpl implements FlipFitGymOwnerDaoInterface {

    @Override
    public int create(int userId, String accountNumber) throws SQLException {
        String sql = "INSERT INTO GymOwner (userId, accountNumber) VALUES (?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setString(2, accountNumber);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert gym owner");
    }

    @Override
    public GymOwner getByUser(User user) throws SQLException {
        String sql = "SELECT u.userId AS u_userId, u.username, u.password, u.email, u.name AS u_name, u.roleId AS u_roleId, " +
                "u.status AS u_status, g.gymOwnerId, g.accountNumber, g.phone, g.panNumber " +
                "FROM User u INNER JOIN GymOwner g ON u.userId = g.userId WHERE u.userId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, user.getUserId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return buildGymOwnerFromResultSet(rs);
            }
        }
        return null;
    }

    // New method: find gym owner by username+password
    @Override
    public GymOwner getGymOwnerByCredentials(String username, String password) throws SQLException {
        String sql = "SELECT u.userId AS u_userId, u.username, u.password, u.email, u.name AS u_name, u.roleId AS u_roleId, " +
                "u.status AS u_status, g.gymOwnerId, g.accountNumber, g.phone, g.panNumber " +
                "FROM User u INNER JOIN GymOwner g ON u.userId = g.userId " +
                "WHERE u.username = ? AND u.password = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return buildGymOwnerFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public void deleteByUserId(int userId) throws SQLException {
        String sql = "DELETE FROM GymOwner WHERE userId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<GymOwner> getByStatus(String status) throws SQLException {
        List<GymOwner> owners = new ArrayList<>();
        String sql = "SELECT u.userId AS u_userId, u.username, u.password, u.email, u.name AS u_name, u.roleId AS u_roleId, " +
                "u.status AS u_status, g.gymOwnerId, g.accountNumber, g.phone, g.panNumber " +
                "FROM User u INNER JOIN GymOwner g ON u.userId = g.userId WHERE u.status = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) owners.add(buildGymOwnerFromResultSet(rs));
            }
        }
        return owners;
    }

    @Override
    public GymOwner get(int gymOwnerId) throws SQLException {
        String sql = "SELECT u.userId AS u_userId, u.username, u.password, u.email, u.name AS u_name, u.roleId AS u_roleId, " +
                "u.status AS u_status, g.gymOwnerId, g.accountNumber, g.phone, g.panNumber " +
                "FROM User u INNER JOIN GymOwner g ON u.userId = g.userId WHERE g.gymOwnerId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, gymOwnerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return buildGymOwnerFromResultSet(rs);
            }
        }
        return null;
    }

    @Override
    public List<GymOwner> getAllGymOwners() throws SQLException {
        List<GymOwner> owners = new ArrayList<>();
        String sql = "SELECT u.userId AS u_userId, u.username, u.password, u.email, u.name AS u_name, u.roleId AS u_roleId, " +
                "u.status AS u_status, g.gymOwnerId, g.accountNumber, g.phone, g.panNumber " +
                "FROM User u INNER JOIN GymOwner g ON u.userId = g.userId";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) owners.add(buildGymOwnerFromResultSet(rs));
        }
        return owners;
    }

    // Helper to map ResultSet -> GymOwner bean
    private GymOwner buildGymOwnerFromResultSet(ResultSet rs) throws SQLException {
        String username = rs.getString("username");
        int userId = rs.getInt("u_userId");
        String password = rs.getString("password");
        String email = rs.getString("email");
        String name = rs.getString("u_name");
        int roleId = rs.getInt("u_roleId");
        String ownerStatus = rs.getString("u_status");

        GymOwner owner = new GymOwner(username, userId, password, email, name, roleId, ownerStatus);

        owner.setGymOwnerId(rs.getInt("gymOwnerId"));
        owner.setAccountNumber(rs.getString("accountNumber"));
        try { owner.setPhone(rs.getString("phone")); } catch (SQLException ignored) {}
        try { owner.setPanNumber(rs.getString("panNumber")); } catch (SQLException ignored) {}
        return owner;
    }
}