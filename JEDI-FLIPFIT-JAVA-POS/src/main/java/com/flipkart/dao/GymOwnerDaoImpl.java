package com.flipkart.dao;

import com.flipkart.bean.FlipFitGym;
import com.flipkart.bean.GymOwner;
import com.flipkart.bean.User;
import com.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GymOwnerDaoImpl implements GymOwnerDaoInterface {
    public void create(int userId, String accountNumber){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO GymOwners (userId, accountNumber) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            int result = stmt.executeUpdate();
            con.close();
            if(result > 0){
                System.out.println("Gym owner created successfully");
            }
            else{
                System.out.println("Gym owner creation failed");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public GymOwner getByUser(User user){
        try{
            int userId = user.getUserId();
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM GymOwners WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println("Gym owner found");
                GymOwner GymOwner = new GymOwner(user.getUsername(), user.getUserId(), user.getPassword(), user.getEmail(), user.getName(), user.getRoleId(), user.getStatus());
                GymOwner.setUserId(rs.getInt("userId"));
                GymOwner.setAccountNumber(rs.getString("accountNumber"));
                GymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                con.close();
                return GymOwner;
            }
            System.out.println("Gym owner not found");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void deleteByUserId(int userId){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM GymOwners WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            int result = stmt.executeUpdate();

//            if(result > 0){
//                System.out.println("Gym owner deleted successfully");
//            }
//            else{
//                System.out.println("Gym owner deletion failed");
//            }
//            System.out.println(result);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public List<GymOwner> getByStatus(String status) {

        List<GymOwner> pendingGymOwners = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM GymOwners LEFT JOIN Users ON GymOwners.userId = Users.userId WHERE Users.status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                GymOwner GymOwner = new GymOwner();
                GymOwner.setGymOwnerId(rs.getInt("GymOwners.gymOwnerId"));
                GymOwner.setAccountNumber(rs.getString("GymOwners.accountNumber"));
                GymOwner.setUserId(rs.getInt("Users.userId"));
                GymOwner.setStatus(rs.getString("Users.status"));
                GymOwner.setUsername(rs.getString("Users.username"));
                GymOwner.setPassword(rs.getString("Users.password"));
                GymOwner.setName(rs.getString("FlipFitUsers.name"));
                GymOwner.setEmail(rs.getString("FlipFitUsers.email"));
                GymOwner.setRoleId(rs.getInt("FlipFitUsers.roleId"));
                pendingGymOwners.add(GymOwner);
            }
            return pendingGymOwners;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return pendingGymOwners;
    }

    @Override
    public List<String> getAreasByCityAndStatus(String city, String status) {
        return List.of();
    }

    @Override
    public List<String> getCitiesByStatus(String status) {
        return List.of();
    }

    @Override
    public void create(String gymName, String gymCity, String gymArea, int gymOwnerId) {

    }

    public GymOwner get(int gymOwnerId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM GymOwners WHERE gymOwnerId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymOwnerId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("Gym owner found");
                GymOwner gymOwner = new GymOwner();
                gymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                gymOwner.setAccountNumber(rs.getString("accountNumber"));
                gymOwner.setUserId(rs.getInt("userId"));
                con.close();
                return gymOwner;
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<FlipFitGym> getAll() {
        return List.of();
    }

    @Override
    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId) {
        return List.of();
    }

    @Override
    public boolean update(String gymName, String gymCity, String gymArea, String gymStatus, int gymOwnerId, int gymId) {
        return false;
    }

    @Override
    public boolean delete(int gymId) {
        return false;
    }

    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> gymOwnersList = new ArrayList<>();

        try {
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT GymOwners.gymOwnerId, GymOwners.accountNumber, " +
                    "FlipFitUsers.userId, FlipFitUsers.username, FlipFitUsers.password, " +
                    "FlipFitUsers.email, FlipFitUsers.name, FlipFitUsers.roleId, FlipFitUsers.status " +
                    "FROM GymOwners " +
                    "JOIN FlipFitUsers ON GymOwners.userId = FlipFitUsers.userId";

            PreparedStatement stmt = con.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a GymOwner object
                GymOwner gymOwner = new GymOwner();
                gymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                gymOwner.setAccountNumber(rs.getString("accountNumber"));

                // Setting user details from FlipFitUsers
                gymOwner.setUserId(rs.getInt("userId"));
                gymOwner.setUsername(rs.getString("username"));
                gymOwner.setPassword(rs.getString("password"));
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setRoleId(rs.getInt("roleId"));
                gymOwner.setStatus(rs.getString("status"));

                // Add the gymOwner object to the list
                gymOwnersList.add(gymOwner);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return gymOwnersList;
    }

}