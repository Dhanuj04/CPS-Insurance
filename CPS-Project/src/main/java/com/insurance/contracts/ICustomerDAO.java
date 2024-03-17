package com.insurance.contracts;

import com.insurance.entity.Customer;
import com.insurance.entity.Insurance;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {

    public boolean insertCustomer(Customer customer) throws SQLException;

    public String validateLogin(String email, String password) throws SQLException;

    public void updateCustomer(String email, String newValue, String columnName);

    public List<Insurance> getAvailableInsurances() throws SQLException;


    public void createOrder(int PolicyId, int UserId, int OrderId) throws SQLException;

    public void updateStatus(int orderId, int userId, double policyPrice) throws SQLException;


}
