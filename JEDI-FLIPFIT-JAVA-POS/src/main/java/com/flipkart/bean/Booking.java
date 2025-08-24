package com.flipkart.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {

    /**
     * The unique identifier for the booking.
     */
    private int bookingId;

    /**
     * The unique identifier for the customer who made the booking.
     */
    private int customerId;

    /**
     * The unique identifier for the gym where the booking was made.
     */
    private int gymId;

    /**
     * The unique identifier for the specific slot that was booked.
     */
    private int slotId;

    /**
     * The date on which the booking is valid.
     */
    private Date bookingDate;

    /**
     * The current status of the booking (e.g., "CONFIRMED", "CANCELLED").
     */
    private String bookingStatus;

    /**
     * The unique identifier for the payment associated with this booking.
     */
    private int paymentId;

    /**
     * The timestamp indicating when the booking was created.
     */
    private Timestamp createdAt;

    /**
     * Constructs a new Booking with all required details.
     *
     * @param customerId The ID of the customer.
     * @param bookingId The ID of the booking.
     * @param bookingStatus The status of the booking.
     * @param paymentId The ID of the associated payment.
     * @param createdAt The timestamp of the booking creation.
     */
    public Booking(int customerId, int bookingId, String bookingStatus, int paymentId, Timestamp createdAt) {
        this.customerId = customerId;
        this.bookingId = bookingId;
        this.bookingStatus = bookingStatus;
        this.paymentId = paymentId;
        this.createdAt = createdAt;
    }

    /**
     * Default constructor for the Booking class.
     * This is required for frameworks that use reflection to create objects.
     */
    public Booking() {

    }

    /**
     * Retrieves the unique identifier of the booking.
     *
     * @return The booking ID.
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Sets the unique identifier for the booking.
     *
     * @param bookingId The new booking ID.
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return The customer ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets the unique identifier for the customer.
     *
     * @param customerId The new customer ID.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves the unique identifier of the gym.
     *
     * @return The gym ID.
     */
    public int getGymId() {
        return gymId;
    }

    /**
     * Sets the unique identifier for the gym.
     *
     * @param gymId The new gym ID.
     */
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    /**
     * Retrieves the unique identifier of the slot.
     *
     * @return The slot ID.
     */
    public int getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique identifier for the slot.
     *
     * @param slotId The new slot ID.
     */
    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the booking date.
     *
     * @return The booking date.
     */
    public Date getBookingDate() {
        return bookingDate;
    }

    /**
     * Sets the booking date.
     *
     * @param bookingDate The new booking date.
     */
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * Retrieves the current status of the booking.
     *
     * @return The booking status string.
     */
    public String getBookingStatus() {
        return bookingStatus;
    }

    /**
     * Sets the status of the booking.
     *
     * @param bookingStatus The new booking status.
     */
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    /**
     * Retrieves the timestamp when the booking was created.
     *
     * @return The creation timestamp.
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp for when the booking was created.
     *
     * @param createdAt The new creation timestamp.
     */
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Retrieves the unique identifier for the associated payment.
     *
     * @return The payment ID.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique identifier for the payment.
     *
     * @param paymentId The new payment ID.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
}