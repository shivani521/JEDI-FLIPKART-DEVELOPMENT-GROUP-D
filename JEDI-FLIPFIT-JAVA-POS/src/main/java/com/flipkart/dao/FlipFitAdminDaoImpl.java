package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;

public class FlipFitAdminDaoImpl implements FlipFitAdminDAOInterface{
//    @Override
//    public FlipFitAdmin getByUsername(String username) {
//        try{
//            int userId = user.getUserId();
//            Connection con = Util.connectToDatabase();
//            String queryStr = "SELECT * FROM FlipFitAdmins WHERE userId = ?";
//            PreparedStatement stmt = con.prepareStatement(queryStr);
//            stmt.setInt(1, userId);
//            ResultSet rs = stmt.executeQuery();
//            if(rs.next()){
//                FlipFitAdmin flipFitAdmin = new FlipFitAdmin(user);
//                flipFitAdmin.setUserId(rs.getInt("userId"));
//                flipFitAdmin.setAdminId(rs.getInt("adminId"));
//                con.close();
//                return flipFitAdmin;
//            }
//            else{
//                System.out.println("Invalid userId");
//            }
//            con.close();
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//        return null;
//    }
}
