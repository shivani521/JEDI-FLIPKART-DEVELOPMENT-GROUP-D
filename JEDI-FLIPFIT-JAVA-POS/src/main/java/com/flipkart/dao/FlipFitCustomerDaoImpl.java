package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import com.flipkart.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The `FlipFitCustomerDaoImpl` class implements the `FlipFitCustomerDAOInterface`
 * to provide the data access layer for customer and user-related operations.
 * It handles all database interactions for customer-specific data.
 */
public class FlipFitCustomerDaoImpl implements FlipFitCustomerDAOInterface {

    /**
     * Creates a new user record in the database.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email The email address of the user.
     * @param name The full name of the user.
     * @param roleId The unique ID of the role assigned to the user.
     * @param status The account status of the user.
     * @return The generated `userId` of the newly created user.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public int createUser(String username, String password, String email, String name, int roleId, String status) throws SQLException {
        String sql = "INSERT INTO User (username, password, email, name, roleId, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, name);
            stmt.setInt(5, roleId);
            stmt.setString(6, status);

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert user");
    }

    /**
     * Creates a new customer record in the database, linking it to an existing user.
     *
     * @param userId The unique ID of the user record to associate with the new customer.
     * @param phone The phone number of the customer.
     * @return The generated `customerId` of the newly created customer.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public int createCustomer(int userId, String phone) throws SQLException {
        String sql = "INSERT INTO Customer (userId, phone) VALUES (?, ?)";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setString(2, phone);

            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to insert customer");
    }

    /**
     * Retrieves a `Customer` object from the database using a `User` object.
     *
     * @param user The `User` object containing generic user details.
     * @return A `Customer` object if a corresponding record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Customer getCustomerByUser(User user) throws SQLException {
        String queryStr = "SELECT * FROM Customer WHERE userId=?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(queryStr)) {
            stmt.setInt(1, user.getUserId());
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Customer customer = new Customer(user.getUsername(), user.getUserId(), user.getPassword(), user.getEmail(), user.getName(), user.getRoleId(), user.getStatus());
                    customer.setCustomerId(result.getInt("customerId"));
                    customer.setPhone(result.getString("phone"));
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves a list of all customers from the database.
     *
     * @return A list of `Customer` objects.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String queryStr = "SELECT u.userId, u.username, u.email, u.name, u.password, u.roleId, u.status, c.customerId, c.phone " +
                "FROM User u INNER JOIN Customer c ON u.userId = c.userId";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(queryStr);
             ResultSet result = stmt.executeQuery()) {
            while (result.next()) {
                Customer customer = new Customer(
                        result.getString("username"),
                        result.getInt("userId"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("name"),
                        result.getInt("roleId"),
                        result.getString("status")
                );
                customer.setCustomerId(result.getInt("customerId"));
                customer.setPhone(result.getString("phone"));
                customers.add(customer);
            }
        }
        return customers;
    }

    /**
     * Retrieves a `Customer` object from the database using a username and password.
     *
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @return A `Customer` object if a matching record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Customer getCustomerByCredentials(String username, String password) throws SQLException {
        String queryStr = "SELECT u.userId, u.username, u.password, u.email, u.name, u.roleId, u.status, c.customerId, c.phone " +
                "FROM User u INNER JOIN Customer c ON u.userId = c.userId " +
                "WHERE u.username = ? AND u.password = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(queryStr)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer(
                            rs.getString("username"),
                            rs.getInt("userId"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getInt("roleId"),
                            rs.getString("status")
                    );
                    customer.setCustomerId(rs.getInt("customerId"));
                    customer.setPhone(rs.getString("phone"));
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Changes a user's password.
     *
     * @param username The username of the user.
     * @param oldPassword The current password of the user.
     * @param newPassword The new password to set.
     * @return `true` if the password was successfully updated for one user; `false` otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean changePassword(String username, String oldPassword, String newPassword) throws SQLException {
        String sql = "UPDATE User SET password = ? WHERE username = ? AND password = ?";
        try (Connection con = DbConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            ps.setString(3, oldPassword);
            int updated = ps.executeUpdate();
            return updated == 1;
        }
    }
}