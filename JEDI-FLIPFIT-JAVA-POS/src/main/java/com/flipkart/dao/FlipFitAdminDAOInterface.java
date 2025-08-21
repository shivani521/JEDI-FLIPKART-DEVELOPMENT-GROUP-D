package com.flipkart.dao;

import com.flipkart.bean.FlipFitAdmin;
import com.flipkart.bean.User;

public interface FlipFitAdminDAOInterface {
    public FlipFitAdmin getByUser(User user);
}