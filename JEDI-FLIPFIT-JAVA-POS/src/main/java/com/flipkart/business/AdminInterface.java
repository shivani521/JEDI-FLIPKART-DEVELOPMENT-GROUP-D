package com.flipkart.business;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;

import java.util.List;

public interface AdminInterface {
    public boolean approveGymOwner (int gymOwnerId, boolean status);
    public List<GymOwner> viewNonApprovedSlots();
    public boolean changePassword(String userName, String oldPassword, String newPassword);
    public void login(String userName, String password);
    public boolean removeGymOwner(int gymOwnerId);
    public void approveSlot(int gymOwnerId);
    public void rejectSlot(int gymOwnerId);

}