package com.flipkart.bean;

/**
 * The `FlipFitGym` class represents a gym entity within the FlipFit system.
 * It encapsulates all the essential details of a gym, including its unique ID,
 * name, location, and operational information.
 */
public class FlipFitGym {

    /**
     * A unique identifier for the gym.
     */
    private int gymId;

    /**
     * The name of the gym.
     */
    private String gymName;

    /**
     * The unique ID of the gym's owner.
     */
    private int ownerId;

    /**
     * The physical address of the gym.
     */
    private String gymAddress;

    /**
     * The total number of available slots for booking at the gym.
     */
    private int numberOfSlots;

    /**
     * The cost of a single booking or session at the gym.
     */
    private int cost;

    /**
     * The current approval status of the gym.
     */
    private int gymStatus;

    /**
     * The GST number of the gym owner.
     */
    private String ownerGstNumber;

    /**
     * Constructs a new `FlipFitGym` object with the provided details.
     *
     * @param gymId The unique ID of the gym.
     * @param gymName The name of the gym.
     * @param ownerId The ID of the gym owner.
     * @param gymAddress The address of the gym.
     * @param numberOfSlots The number of slots available.
     * @param cost The cost per session.
     * @param gymStatus The approval status of the gym.
     * @param ownerGstNumber The GST number of the gym owner.
     */
    public FlipFitGym(int gymId, String gymName, int ownerId,  String gymAddress, int numberOfSlots, int cost, int gymStatus, String ownerGstNumber) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.ownerId = ownerId;
        this.gymAddress = gymAddress;
        this.numberOfSlots = numberOfSlots;
        this.cost = cost;
        this.gymStatus = gymStatus;
        this.ownerGstNumber = ownerGstNumber;
    }

    /**
     * Default constructor for the `FlipFitGym` class.
     */
    public FlipFitGym() {

    }

    /**
     * Retrieves the number of available slots.
     *
     * @return The number of slots.
     */
    public int getNumberOfSlots() {
        return numberOfSlots;
    }

    /**
     * Sets the number of available slots.
     *
     * @param numberOfSlots The new number of slots.
     */
    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots = numberOfSlots;
    }

    /**
     * Retrieves the unique ID of the gym.
     *
     * @return The gym's ID.
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * Sets the unique ID of the gym.
     *
     * @param gymId The new gym ID.
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    /**
     * Retrieves the name of the gym.
     *
     * @return The gym's name.
     */
    public String getGymName() {
        return gymName;
    }

    /**
     * Sets the name of the gym.
     *
     * @param gymName The new name for the gym.
     */
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    /**
     * Retrieves the ID of the gym's owner.
     *
     * @return The owner's ID.
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the ID of the gym's owner.
     *
     * @param ownerId The new owner ID.
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Retrieves the address of the gym.
     *
     * @return The gym's address.
     */
    public String getGymAddress() {
        return gymAddress;
    }

    /**
     * Sets the address of the gym.
     *
     * @param gymAddress The new gym address.
     */
    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    /**
     * Retrieves the cost of a session.
     *
     * @return The cost of a session.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the cost of a session.
     *
     * @param cost The new cost.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Retrieves the approval status of the gym.
     *
     * @return The gym's status code.
     */
    public int getGymStatus() {
        return gymStatus;
    }

    /**
     * Sets the approval status of the gym.
     *
     * @param gymStatus The new status code.
     */
    public void setGymStatus(int gymStatus) {
        this.gymStatus = gymStatus;
    }

    /**
     * Retrieves the GST number of the gym owner.
     *
     * @return The owner's GST number.
     */
    public String getOwnerGstNumber() {
        return ownerGstNumber;
    }

    /**
     * Sets the GST number of the gym owner.
     *
     * @param ownerGstNumber The new GST number.
     */
    public void setOwnerGstNumber(String ownerGstNumber) {
        this.ownerGstNumber = ownerGstNumber;
    }

    /**
     * Sets the city for the gym.
     *
     * @param city The city of the gym.
     */
    public void setCity(String city) {
    }

    /**
     * Sets the address of the gym.
     *
     * @param address The address of the gym.
     */
    public void setAddress(String address) {
    }

    /**
     * Sets the status of the gym.
     *
     * @param status The new status.
     */
    public void setStatus(String status) {
    }
}