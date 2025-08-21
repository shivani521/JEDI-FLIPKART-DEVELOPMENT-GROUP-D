package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import java.sql.SQLException;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    int createUser(String username, String password, String email, String name, int roleId, String status) throws SQLException;
    int createCustomer(int userId, String phone) throws SQLException;
    Customer getCustomerByUser(User user) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
}