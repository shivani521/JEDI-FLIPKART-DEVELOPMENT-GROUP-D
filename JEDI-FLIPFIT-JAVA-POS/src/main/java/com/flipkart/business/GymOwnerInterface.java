//package com.flipkart.business;
//
//import com.flipkart.bean.Booking;
//import com.flipkart.bean.FlipFitGym;
//import com.flipkart.bean.Slot;
//
//import java.util.List;
//
//public interface GymOwnerInterface {
//    public boolean login (String userName, String password);
//    public boolean register (String userId, String userName, String email, String password, String adharCardNumber,
//                             List<String> gymCenterId);
//    public boolean addCenter (String ownerId, String gymId, String city, int capacity, int cost);
//    public boolean removeCenter (String ownerId, String gymId);
//    public boolean changePassword(String username, String oldPassword, String newPassword);
//    public List<FlipFitGym> getPendingGyms();
//    public List<Slot> getPendingSlots();
//    public List<Booking> getAllBookings();
//}

package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.Slot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface GymOwnerInterface {
    /// //////Gym owner services///////
    public boolean login (String userName, String password);
    public boolean register (int userId, String userName, String email, String password, String adharCardNumber,
                             List<Integer> gymCenterId,String status);
    public GymOwner createGymOwner(int userId, String userName, String email, String password, String phoneNumber,String adharCardNumber,String panNumber,List<Integer> gymCenterId,boolean status) ;
    public boolean createGym(int gymId,String gymName,int ownerId,String ownerName,String gymAddress,int numberOfSlots,int cost,int gymStatus,String ownerGstNumber);
    public void deleteGym(int gymId);
    public void updateGym();
    //public boolean addCenter (int ownerId, int gymId, String city, int capacity, int cost);
    //public boolean removeCenter (int ownerId, int gymId);
    /// //////////slot services/////
    public boolean createSlot( int slotId,int gymId,LocalDate slotDate, LocalTime slotTime);
    public boolean removeSlot(int slotId);
    public List<Slot> getPendingSlots();
    public List<Booking> getAllBookings();
    public List<FlipFitGym> getPendingGyms();


}