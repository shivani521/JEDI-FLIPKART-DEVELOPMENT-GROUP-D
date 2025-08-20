package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;

import java.util.List;

public class GymOwnerService implements GymOwnerInterface {
    public static List<GymOwner> getPendingGymOwners() {
        System.out.println("to be added");
        return List.of();
    }

    @Override
    public boolean login(String userName, String password) {
        return false;
    }

    @Override
    public boolean register(String userId, String userName, String email, String password, String adharCardNumber, List<String> gymCenterId) {
        return false;
    }

    @Override
    public boolean addCenter(String ownerId, String gymId, String city, int capacity, int cost) {
        return false;
    }

    @Override
    public boolean removeCenter(String ownerId, String gymId) {
        return false;
    }

    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public List<FlipFitGym> getPendingGyms() {
        return List.of();
    }

    @Override
    public List<Slot> getPendingSlots() {
        return List.of();
    }
}
