package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import java.util.List;

public interface FlipFitCustomerDAOInterface {
    public void createCustomer(int customerId, int userId, String phone);
    public Customer getCustomerByUser(User user);
    public List<Customer> getAllCustomers();
}