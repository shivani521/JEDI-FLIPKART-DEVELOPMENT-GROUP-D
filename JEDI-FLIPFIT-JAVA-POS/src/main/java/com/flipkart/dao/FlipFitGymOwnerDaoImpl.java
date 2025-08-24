package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `FlipFitGymOwnerDaoImpl` class implements the `FlipFitGymOwnerDaoInterface`
 * to provide the data access layer for gym owner-related operations.
 * It handles all database interactions for gym owner data, including creation,
 * retrieval, and deletion of records.
 */
public class FlipFitGymOwnerDaoImpl implements FlipFitGymOwnerDaoInterface {

    /**
     * Creates a new gym owner record in the database, linking it to an existing user.
     *
     * @param userId The unique ID of the user record to associate with the new gym owner.
     * @param accountNumber The bank account number of the gym owner.
     * @return The generated `gymOwnerId` of the newly created gym owner.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a `GymOwner` object from the database using a `User` object.
     *
     * @param user The `User` object containing the generic user details.
     * @return A `GymOwner` object if a corresponding record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a `GymOwner` object from the database using a username and password.
     *
     * @param username The username of the gym owner.
     * @param password The password of the gym owner.
     * @return A `GymOwner` object if a matching record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Deletes a gym owner record from the database by their user ID.
     *
     * @param userId The unique ID of the user whose gym owner record is to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void deleteByUserId(int userId) throws SQLException {
        String sql = "DELETE FROM GymOwner WHERE userId = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves a list of all gym owners who have a specific status.
     *
     * @param status The status to filter gym owners by (e.g., "PENDING," "APPROVED").
     * @return A list of `GymOwner` objects with the specified status.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a single `GymOwner` object from the database using their unique gym owner ID.
     *
     * @param gymOwnerId The unique ID of the gym owner to retrieve.
     * @return The `GymOwner` object if found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Retrieves a list of all gym owners in the system.
     *
     * @return A list of all `GymOwner` objects.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Helper method to construct a `GymOwner` object from a `ResultSet`.
     * This method handles mapping the database query results to the Java bean.
     *
     * @param rs The `ResultSet` containing the gym owner data.
     * @return A `GymOwner` object populated with data from the `ResultSet`.
     * @throws SQLException if a database access error occurs.
     */
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