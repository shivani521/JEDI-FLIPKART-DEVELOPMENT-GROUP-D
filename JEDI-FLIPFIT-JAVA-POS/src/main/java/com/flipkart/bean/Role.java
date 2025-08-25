package com.flipkart.bean;

/**
 * The `Role` class represents a user role within the FlipFit system.
 * It contains a unique role ID and the name of the role.
 */
public class Role {

    /**
     * A unique identifier for the role.
     */
    private int roleId;

    /**
     * The name of the role (e.g., "Customer", "GymOwner", "Admin").
     */
    private String roleName;

    /**
     * Retrieves the unique ID of the role.
     *
     * @return The role ID.
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * Sets the unique ID for the role.
     *
     * @param roleId The new role ID.
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * Retrieves the name of the role.
     *
     * @return The role name.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the name of the role.
     *
     * @param roleName The new role name.
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}