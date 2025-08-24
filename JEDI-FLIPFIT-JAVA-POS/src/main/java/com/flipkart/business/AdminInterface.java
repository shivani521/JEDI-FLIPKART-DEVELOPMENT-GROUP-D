package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;

import java.util.List;

/**
 * The `AdminInterface` defines the business logic and operations that can be performed by an administrator.
 * It provides methods for managing gym owners, gym slots, and user authentication.
 */
public interface AdminInterface {

    /**
     * Approves or rejects a gym owner based on their unique ID.
     *
     * @param gymOwnerId The unique ID of the gym owner to be approved or rejected.
     * @param status The approval status to set (`true` for approved, `false` for rejected).
     * @return `true` if the operation was successful, `false` otherwise.
     */
    public boolean approveGymOwner (int gymOwnerId, boolean status);

    /**
     * Retrieves a list of all gym owners who are not yet approved.
     *
     * @return A list of `GymOwner` objects with a pending status.
     */
    public List<GymOwner> viewNonApprovedSlots();

    /**
     * Changes the password for a user.
     *
     * @param userName The username of the user.
     * @param oldPassword The current password of the user.
     * @param newPassword The new password to be set.
     * @return `true` if the password was successfully changed, `false` otherwise.
     */
    public boolean changePassword(String userName, String oldPassword, String newPassword);

    /**
     * Authenticates an administrator for login.
     *
     * @param userName The username of the administrator.
     * @param password The password of the administrator.
     * @return `true` if the login credentials are valid, `false` otherwise.
     */
    public boolean login(String userName, String password);

    /**
     * Removes a gym owner from the system by their unique ID.
     *
     * @param gymOwnerId The unique ID of the gym owner to be removed.
     * @return `true` if the removal was successful, `false` otherwise.
     */
    public boolean removeGymOwner(int gymOwnerId);

    /**
     * Approves all slots associated with a specific gym owner.
     *
     * @param gymOwnerId The unique ID of the gym owner whose slots are to be approved.
     */
    public void approveSlot(int gymOwnerId);

    /**
     * Rejects all slots associated with a specific gym owner.
     *
     * @param gymOwnerId The unique ID of the gym owner whose slots are to be rejected.
     */
    public void rejectSlot(int gymOwnerId);

}