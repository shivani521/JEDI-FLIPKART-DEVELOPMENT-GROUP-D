package com.flipkart.dao;

import com.flipkart.bean.Customer;
import com.flipkart.bean.User;
import java.sql.SQLException;
import java.util.List;

/**
 * The `FlipFitCustomerDAOInterface` defines the data access object (DAO) contract
 * for customer-related entities. It specifies the methods for interacting with a data source
 * to perform operations related to customer data, including user account management and retrieval.
 */
public interface FlipFitCustomerDAOInterface {

    /**
     * Creates a new user record in the data source.
     *
     * @param username The username for the new user.
     * @param password The password for the new user.
     * @param email The email address of the user.
     * @param name The full name of the user.
     * @param roleId The unique ID of the role assigned to the user.
     * @param status The account status of the user.
     * @return The generated `userId` of the newly created user.
     * @throws SQLException if a database access error occurs.
     */
    int createUser(String username, String password, String email, String name, int roleId, String status) throws SQLException;

    /**
     * Creates a new customer record in the data source, linking it to an existing user.
     *
     * @param userId The unique ID of the user record to associate with the new customer.
     * @param phone The phone number of the customer.
     * @return The generated `customerId` of the newly created customer.
     * @throws SQLException if a database access error occurs.
     */
    int createCustomer(int userId, String phone) throws SQLException;

    /**
     * Retrieves a `Customer` object from the data source using a `User` object.
     * This method is used to fetch customer-specific details after generic user information is available.
     *
     * @param user The `User` object containing the generic user details.
     * @return A `Customer` object if a corresponding record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    Customer getCustomerByUser(User user) throws SQLException;

    /**
     * Retrieves a list of all customers from the data source.
     *
     * @return A list of all `Customer` objects.
     * @throws SQLException if a database access error occurs.
     */
    List<Customer> getAllCustomers() throws SQLException;

    /**
     * Authenticates a customer by their credentials and retrieves their full `Customer` object.
     * This method joins `User` and `Customer` data to return a complete customer record.
     *
     * @param username The username of the customer.
     * @param password The password of the customer.
     * @return A `Customer` object if a matching record is found; otherwise, `null`.
     * @throws SQLException if a database access error occurs.
     */
    Customer getCustomerByCredentials(String username, String password) throws SQLException;
}