package com.flipkart.exceptions;

public class IncorrectSlotDetailsEnteredException extends Exception {
    public IncorrectSlotDetailsEnteredException() {

    }
    public String getMessage() {
        return "Slot details entered incorrectly.\n";
    }
}
