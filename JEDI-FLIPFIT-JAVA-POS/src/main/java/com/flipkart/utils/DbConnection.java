package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections to the FlipFitSchema.
 * This class provides a static method to get a new connection, centralizing
 * the database credentials and logic.
 */
public class DbConnection {

    /**
     * The JDBC URL for the MySQL database.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/FlipFitSchema";

    /**
     * The username for the database connection.
     */
    private static final String USER = "root";

    /**
     * The password for the database connection.
     */
    private static final String PASSWORD = "ShivChhatrapati@19";

    /**
     * Establishes and returns a new connection to the database.
     * <p>
     * This method uses the {@code DriverManager} to create a connection
     * with the predefined URL, user, and password. The caller is responsible
     * for handling the {@code SQLException} and for closing the connection
     * after use to free up resources.
     * </p>
     * @return A new {@link Connection} object.
     * @throws SQLException if a database access error occurs or the URL is null.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}