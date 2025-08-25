package com.flipkart.bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 * The `Slot` class represents a time slot available for booking at a gym.
 * It contains details about the duration, availability, and pricing of a slot.
 */
public class Slot {

    /**
     * A unique identifier for the slot.
     */
    private int slotId;

    /**
     * The status of the slot, e.g., "Available", "Booked".
     */
    private String status;

    /**
     * The unique ID of the gym to which this slot belongs.
     */
    private int gymId;

    /**
     * The start time of the slot.
     */
    private Time startTime;

    /**
     * The end time of the slot.
     */
    private Time endTime;

    /**
     * The number of seats currently available for booking.
     */
    private int seatsAvailable;

    /**
     * The price of booking this slot.
     */
    private double price;

    /**
     * The date on which the slot is valid.
     */
    private Date slotDate;

    /**
     * The total number of seats originally available in this slot.
     */
    private int totalSeats;

    /**
     * Constructs a new `Slot` object with a unique slot ID.
     *
     * @param slotId The unique identifier for the slot.
     */
    public Slot(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Constructs a new `Slot` object with all its properties.
     *
     * @param status The status of the slot.
     * @param slotId The unique ID of the slot.
     * @param gymId The ID of the gym.
     * @param startTime The start time of the slot.
     * @param endTime The end time of the slot.
     * @param seatsAvailable The number of seats available for booking.
     * @param price The price of the slot.
     * @param slotDate The date of the slot.
     * @param totalSeats The total number of seats in the slot.
     */
    public Slot(String status, int slotId, int gymId, Time startTime, Time endTime, int seatsAvailable, double price, Date slotDate, int totalSeats) {
        this.status = status;
        this.slotId = slotId;
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
        this.slotDate = slotDate;
        this.totalSeats = totalSeats;
    }

    /**
     * Retrieves the status of the slot.
     *
     * @return The status of the slot.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the slot.
     *
     * @param status The new status for the slot.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the unique ID of the slot.
     *
     * @return The slot ID.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique ID of the slot.
     *
     * @param slotId The new slot ID.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the ID of the gym.
     *
     * @return The gym ID.
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * Sets the ID of the gym.
     *
     * @param gymId The new gym ID.
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    /**
     * Retrieves the start time of the slot.
     *
     * @return The start time.
     */
    public Time getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the slot.
     *
     * @param startTime The new start time.
     */
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the slot.
     *
     * @return The end time.
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the slot.
     *
     * @param endTime The new end time.
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the number of available seats.
     *
     * @return The number of available seats.
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    /**
     * Sets the number of available seats.
     *
     * @param seatsAvailable The new number of available seats.
     */
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /**
     * Retrieves the price of the slot.
     *
     * @return The price of the slot.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the slot.
     *
     * @param price The new price for the slot.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the total number of seats in the slot.
     *
     * @return The total number of seats.
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * Sets the total number of seats in the slot.
     *
     * @param totalSeats The new total number of seats.
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * Retrieves the date of the slot.
     *
     * @return The slot date.
     */
    public Date getSlotDate() {
        return slotDate;
    }

    /**
     * Sets the date of the slot.
     *
     * @param slotDate The new slot date.
     */
    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }
}