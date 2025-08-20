package com.flipkart.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Slot {
    private int slotId;
    private int gymId;
    private LocalDate slotDate;
    private LocalTime slotTime;

    public Slot(int slotId, int gymId, LocalDate date, LocalTime slotTime) {
        this.slotId = slotId;
        this.gymId = gymId;
        this.slotDate = date;
        this.slotTime = slotTime;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public LocalDate getSlotDate() {return slotDate;}

    public void setSlotDate(LocalDate slotDate) { this.slotDate = slotDate;}

    public LocalTime getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(LocalTime slotTime) {
        this.slotTime = slotTime;
    }
}