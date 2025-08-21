package com.flipkart.business;

import com.flipkart.bean.GymOwner;

import java.util.List;

public class AdminService implements AdminInterface{
    @Override
    public  boolean approveGymOwner(int gymOwnerId, boolean status) {
        System.out.println("Approving Gym Owner " + gymOwnerId);
        return false;
    }

    @Override
    public List<GymOwner> viewNonApprovedSlots()
    {
        System.out.println("Viewing non approved Gym Owner slots ");
        return List.of();
    }

    @Override
    public boolean changePassword(String userName, String oldPassword, String newPassword) {
        System.out.println("Changing Password");
        return false;
    }

    @Override
    public void login(String userName, String password) {

    }

    @Override
    public boolean removeGymOwner(int gymOwnerId)
    {
        System.out.println("Removing Gym Owner " + gymOwnerId);
        return false;
    }

    @Override
    public void approveSlot(int gymOwnerId) {
        System.out.println("Approving Gym Owner " + gymOwnerId);
    }

    @Override
    public void rejectSlot(int gymOwnerId) {
        System.out.println("Rejecting Gym Owner " + gymOwnerId);
    }
}