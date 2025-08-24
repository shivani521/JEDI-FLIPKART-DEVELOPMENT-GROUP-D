package com.flipkart.exceptions;

/**
 * Thrown when a user's registration (e.g., a gym owner) is pending approval from the administrator.
 * This indicates that the account is in a waiting state and cannot be used yet.
 * This is a custom checked exception.
 */
public class RegistrationPendingAtAdmin extends Exception {

    /**
     * The type of entity (e.g., "Gym Owner") whose registration is pending.
     */
    String entity;

    /**
     * Constructs a new RegistrationPendingAtAdmin exception with the specified entity type.
     *
     * @param entity The entity type (e.g., "Gym Owner") whose registration is pending.
     */
    public RegistrationPendingAtAdmin(String entity) {
        this.entity = entity;
    }

    /**
     * Returns a detailed message about the exception.
     *
     * @return The error message indicating that registration is pending for the specified entity.
     */
    @Override
    public String getMessage() {
        return entity + "registration pending at admin";
    }
}