package com.flipkart.business;

import com.flipkart.bean.GymOwner;

import java.util.List;

public class AdminService implements AdminInterface{
    @Override
    public static boolean approveGymOwner(int gymOwnerId, boolean status) {
        return false;
    }

    @Override
    public List<GymOwner> viewNonApprovedSlots() {
        return List.of();
    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public void login(String userName, String password) {

    }

    @Override
    public boolean removeGymOwner(int gymOwnerId) {
        return false;
    }
}
