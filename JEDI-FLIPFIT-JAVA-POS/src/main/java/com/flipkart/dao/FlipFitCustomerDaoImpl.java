package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import com.flipkart.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerDaoImpl implements FlipFitCustomerDAOInterface {

    @Override
    public void createCustomer(int customerId, int userId, String phone) {
        try {
            Connection con = DbConnection.getConnection();
            String queryStr = "INSERT INTO FlipFitCustomers (customerId,userId, phone) VALUES(?,?,?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            stmt.setString(2, phone);
            int result = stmt.executeUpdate();
            if (result > 0) {
                System.out.println("Customer created successfully!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Customer getCustomerByUser(User user) {
        try {
            Connection con = DbConnection.getConnection();
            String queryStr = "SELECT * FROM FlipFitCustomers WHERE userId=?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, user.getUserId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Customer customer = new Customer(user.getUsername(), user.getUserId(), user.getPassword(), user.getEmail(), user.getName(), user.getRoleId(), user.getStatus());
                customer.setCustomerId(result.getInt("customerId"));
                customer.setPhone(result.getString("phone"));
                return customer;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection con = DbConnection.getConnection();
            String queryStr = "SELECT u.userId, u.username, u.email, u.name, u.password, u.roleId, u.status, c.customerId, c.phone " +
                    "FROM FlipFitUsers u " +
                    "INNER JOIN FlipFitCustomers c ON u.userId = c.userId";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                Customer customer = new Customer(
                        result.getString("username"),
                        result.getInt("userId"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("name"),
                        result.getInt("roleId"),
                        result.getString("status")
                );
                customer.setCustomerId(result.getInt("customerId"));
                customer.setPhone(result.getString("phone"));
                customers.add(customer);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return customers;
    }
}