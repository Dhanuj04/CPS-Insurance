package com.insurance.dao;

import com.insurance.connections.DatabaseConnection;
import com.insurance.contracts.ICustomerDAO;
import com.insurance.entity.Customer;
import com.insurance.entity.Insurance;
import com.insurance.entity.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomerDAO implements ICustomerDAO {

    private static final String INSERT_CUSTOMER_QUERY = "INSERT INTO customers (UserId, Name, Email, Password, DoB, City, State, Credits) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customers SET %s = ? WHERE email = ?";
    private static final String SELECT_AVAILABLE_INSURANCES_QUERY = "SELECT * FROM insurance WHERE status = 'Active'";
    private static final String CREATE_ORDER_QUERY = "INSERT INTO Orders (OrderId, UserId, PolicyID, PaymentStatus) VALUES (?, ?, ?, ?)";
    private static final String REGISTERED_INSURANCES_QUERY = "SELECT Orders.OrderID, Orders.UserId, Orders.PolicyID, Insurance.PolicyName, Insurance.PolicyType, Insurance.Tenure, Insurance.PolicyPrice, Insurance.Coverage, Insurance.Status FROM Orders INNER JOIN Insurance ON Orders.PolicyID = Insurance.PolicyID";
    private static final String INSERT_TRANSACTION_QUERY = "INSERT INTO Transaction (TransactionID, Orderid, UserId, StartDate, PolicyPrice, PaymentStatus, ActiveStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TRANSACTION_QUERY = "UPDATE Transaction t JOIN Orders o ON t.OrderId = o.OrderID JOIN Insurance i ON o.PolicyID = i.PolicyID SET t.EndDate = DATE_ADD(t.StartDate, INTERVAL i.Tenure YEAR)";

    public boolean insertCustomer(Customer customer) throws SQLException {

        PreparedStatement statement = DatabaseConnection.getMySqlConnection().prepareStatement(INSERT_CUSTOMER_QUERY);
        statement.setInt(1, customer.getUserId());
        statement.setString(2, customer.getName());
        statement.setString(3, customer.getEmail());
        statement.setString(4, customer.getPassword());
        statement.setDate(5, Date.valueOf(customer.getDob()));
        statement.setString(6, customer.getCity());
        statement.setString(7, customer.getState());
        statement.setDouble(8, customer.getCredits());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
    }

    public String validateLogin(String email, String password) throws SQLException {
        String query = "SELECT Name FROM customers WHERE Email = ? AND Password = ?";
        try (PreparedStatement preparedStatement = DatabaseConnection.getMySqlConnection().prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Name"); // Return user name if login is successful
                } else {
                    return null; // Return null if login is unsuccessful
                }
            }
        }
    }

    public List<Insurance> getAvailableInsurances() {
        List<Insurance> availableInsurances = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_INSURANCES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Insurance insurance = extractInsuranceFromResultSet(resultSet);
                availableInsurances.add(insurance);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return availableInsurances;
    }

    public void updateCustomer(String email, String newValue, String columnName) {
        String updateQuery = String.format(UPDATE_CUSTOMER_QUERY, columnName);
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newValue);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Profile updated successfully");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void createOrder(int orderId, int userId, int policyId) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_QUERY)) {

            statement.setInt(1, orderId);
            statement.setInt(2, userId);
            statement.setInt(3, policyId);
            statement.setString(4, "Inactive");

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order created successfully.");
            } else {
                System.out.println("Failed to create order.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> registeredInsurances() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(REGISTERED_INSURANCES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Order order = extractOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }

    public void updateStatus(int orderId, int userId, double policyPrice) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement insertStatement = connection.prepareStatement(INSERT_TRANSACTION_QUERY);
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_TRANSACTION_QUERY)) {

            String paymentStatus = "Paid";
            String activeStatus = "Active";
            long seed = System.currentTimeMillis();
            Random random = new Random(seed);
            int transactionId = random.nextInt(9000000) + 1000000;

            insertStatement.setInt(1, transactionId);
            insertStatement.setInt(2, orderId);
            insertStatement.setInt(3, userId);
            insertStatement.setDate(4, Date.valueOf(LocalDate.now()));
            insertStatement.setDouble(5, policyPrice);
            insertStatement.setString(6, paymentStatus);
            insertStatement.setString(7, activeStatus);

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment status updated successfully.");
                updateStatement.executeUpdate();
            } else {
                System.out.println("No records were updated.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Insurance extractInsuranceFromResultSet(ResultSet resultSet) throws SQLException {
        Insurance insurance = new Insurance();
        insurance.setInsuranceCode(resultSet.getInt("PolicyID"));
        insurance.setPolicyName(resultSet.getString("PolicyName"));
        insurance.setPolicyType(resultSet.getString("PolicyType"));
        insurance.setTenure(resultSet.getInt("Tenure"));
        insurance.setPrice(resultSet.getDouble("PolicyPrice"));
        insurance.setCoverage(resultSet.getInt("Coverage"));
        insurance.setStatus(resultSet.getString("Status"));
        return insurance;
    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderID(resultSet.getInt("OrderID"));
        order.setUserID(resultSet.getInt("UserId"));
        order.setPolicyID(resultSet.getInt("PolicyID"));
        order.setPolicyName(resultSet.getString("PolicyName"));
        order.setPolicyType(resultSet.getString("PolicyType"));
        order.setTenure(resultSet.getInt("Tenure"));
        order.setPolicyPrice(resultSet.getDouble("PolicyPrice"));
        order.setCoverage(resultSet.getInt("Coverage"));
        order.setStatus(resultSet.getString("Status"));
        return order;
    }
}
