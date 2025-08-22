package com.flipkart.dao;

import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;

import java.sql.SQLException;
import java.util.List;

public interface FlipFitGymOwnerDaoInterface {
    // Create a gym owner record for a given user. Returns generated gymOwnerId.
    int create(int userId, String accountNumber) throws SQLException;

    // Retrieve GymOwner by linked User object (joins User + GymOwner)
    GymOwner getByUser(User user) throws SQLException;

    // New: Authenticate gym owner by username+password (returns GymOwner if found)
    GymOwner getGymOwnerByCredentials(String username, String password) throws SQLException;

    // Delete gym owner entry by userId
    void deleteByUserId(int userId) throws SQLException;

    // Get gym owners by status (e.g., "PENDING", "ACTIVE", "INACTIVE")
    List<GymOwner> getByStatus(String status) throws SQLException;

    // Get a GymOwner by its gymOwnerId (primary key)
    GymOwner get(int gymOwnerId) throws SQLException;

    // Get all gym owners (join User + GymOwner)
    List<GymOwner> getAllGymOwners() throws SQLException;
}