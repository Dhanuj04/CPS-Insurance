package com.insurance.contracts;


import java.sql.SQLException;

public interface ICustomerService {

    public void registerNewCustomer(String name, String email, String password, String dob, String city, String state) throws SQLException;

    public String validateLogin(String email, String password) throws SQLException;
    public void updateCustomer(String email);

    public void viewAvailableInsurance();

    public void updatePaymentStatus(int orderId, int userId, double policyPrice) throws SQLException;
}
