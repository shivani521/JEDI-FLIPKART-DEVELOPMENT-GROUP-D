package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * The `FlipFitGymOwnerDaoInterface` defines the data access object (DAO) contract
 * for `GymOwner` entities. It specifies the methods for interacting with a data source
 * (e.g., a database) to perform operations related to gym owner data.
 */
public interface FlipFitGymOwnerDaoInterface {
    /**
     * Creates a new gym owner record in the data source, linking it to an existing user.
     *
     * @param userId The unique ID of the user record to associate with the new gym owner.
     * @param accountNumber The bank account number of the gym owner.
     * @return The generated `gymOwnerId` of the newly created gym owner.
     * @throws SQLException if a database access error occurs.
     */
    int create(int userId, String accountNumber) throws SQLException;

    /**
     * Retrieves a `GymOwner` object from the data source using a `User` object.
     * This method is typically used to fetch gym owner-specific details after a generic user login.
     *
     * @param user The `User` object containing the generic user details.
     * @return A `GymOwner` object if a corresponding record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    GymOwner getByUser(User user) throws SQLException;

    /**
     * Authenticates a gym owner by their credentials and retrieves their full `GymOwner` object.
     *
     * @param username The username of the gym owner.
     * @param password The password of the gym owner.
     * @return A `GymOwner` object if a matching record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    GymOwner getGymOwnerByCredentials(String username, String password) throws SQLException;

    /**
     * Deletes a gym owner record from the data source by their user ID.
     *
     * @param userId The unique ID of the user whose gym owner record is to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    void deleteByUserId(int userId) throws SQLException;

    /**
     * Retrieves a list of all gym owners who have a specific status.
     * This can be used to filter for pending, approved, or other statuses.
     *
     * @param status The status to filter gym owners by (e.g., "PENDING," "ACTIVE").
     * @return A list of `GymOwner` objects with the specified status.
     * @throws SQLException if a database access error occurs.
     */
    List<GymOwner> getByStatus(String status) throws SQLException;

    /**
     * Retrieves a single `GymOwner` object from the data source using their unique gym owner ID.
     *
     * @param gymOwnerId The unique ID of the gym owner to retrieve.
     * @return The `GymOwner` object if found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    GymOwner get(int gymOwnerId) throws SQLException;

    /**
     * Retrieves a list of all gym owners in the system.
     *
     * @return A list of all `GymOwner` objects.
     * @throws SQLException if a database access error occurs.
     */
    List<GymOwner> getAllGymOwners() throws SQLException;
}