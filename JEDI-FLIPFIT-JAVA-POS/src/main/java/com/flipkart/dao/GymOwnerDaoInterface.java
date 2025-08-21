package com.flipkart.dao;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;

import java.util.List;

public interface GymOwnerDaoInterface {
    public void create(String gymName, String gymCity, String gymArea, int gymOwnerId);
    public GymOwner get(int gymId);
    public List<FlipFitGym> getAll();
    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId);
    public boolean update(String gymName, String gymCity, String gymArea, String gymStatus, int gymOwnerId, int gymId);
    public boolean delete(int gymId);
    public List<GymOwner> getByStatus(String status);
    public List<String> getAreasByCityAndStatus(String city, String status);
    public List<String> getCitiesByStatus(String status);
}
