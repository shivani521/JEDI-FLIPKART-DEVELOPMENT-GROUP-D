package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.User;
import com.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAOInterface{
    public FlipFitAdmin getByUser(User user){
        try{
            int userId = user.getUserId();
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitAdmins WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                FlipFitAdmin flipFitAdmin;
                flipFitAdmin = new FlipFitAdmin(user.getUsername(),
                        user.getUserId(),
                        user.getPassword(),
                        user.getEmail(),
                        user.getName(),
                        user.getRoleId(),
                        user.getStatus());
                flipFitAdmin.setUserId(rs.getInt("userId"));
                flipFitAdmin.setAdminId(rs.getInt("adminId"));
                con.close();
                return flipFitAdmin;
            }
            else{
                System.out.println("Invalid userId");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


}