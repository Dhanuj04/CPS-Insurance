package com.insurance.dao;

import com.insurance.connections.DatabaseConnection;
import com.insurance.contracts.IEmployeeDAO;
import com.insurance.entity.Employee;
import com.insurance.entity.Policy;

import java.sql.*;
import java.time.LocalDate;

public class EmployeeDAO implements IEmployeeDAO {

    private static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO employees (employee_id, emp_name, emp_login_id, password) VALUES (?, ?, ?, ?)";
    private static final String SELECT_EMPLOYEE_BY_ID_QUERY = "SELECT * FROM employees WHERE employee_id = ?";
    private static final String SELECT_EMPLOYEE_BY_LOGIN_QUERY = "SELECT * FROM employees WHERE emp_login_id = ? AND password = ?";
    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM employees WHERE employee_id = ?";
    private static final String SELECT_POLICY_DETAILS_QUERY = "SELECT i.PolicyID, i.PolicyName, i.PolicyType, i.Tenure, i.Coverage, i.PolicyPrice, t.StartDate FROM Orders o JOIN Transaction t ON o.OrderID = t.OrderID JOIN Insurance i ON o.PolicyID = i.PolicyID WHERE i.PolicyID = ? and o.UserId=?";
    private static final String UPDATE_PREMIUM_AMOUNT_QUERY = "UPDATE Transaction t JOIN Orders o ON t.OrderId = o.OrderID SET t.PolicyPrice = ? WHERE o.PolicyID = ?";
    private static final String UPDATE_POLICY_CLOSURE_DATE_QUERY = "UPDATE Transaction t JOIN Orders o ON t.OrderId = o.OrderID SET t.EndDate = ? , t.ActiveStatus = 'Pre-Closure' WHERE o.PolicyID = ?";
    private static final String HAND_OVER_POLICY_QUERY = "UPDATE Transaction t JOIN Orders o ON t.OrderId = o.OrderID SET t.ActiveStatus = 'Hand Over Policy' WHERE o.PolicyID = ?";

    public void createEmployee(Employee employee) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_QUERY)) {
            preparedStatement.setString(1, employee.getEmployeeId());
            preparedStatement.setString(2, employee.getEmpName());
            preparedStatement.setString(3, employee.getEmpLoginId());
            preparedStatement.setString(4, employee.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Employee getById(String employeeId) {
        Employee employee = null;
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID_QUERY)) {
            preparedStatement.setString(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String empName = resultSet.getString("emp_name");
                    String empLoginId = resultSet.getString("emp_login_id");
                    String password = resultSet.getString("password");
                    employee = new Employee(employeeId, empName, empLoginId, password);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    public Employee getByLoginIdAndPassword(String empLoginId, String password) {
        Employee employee = null;
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_LOGIN_QUERY)) {
            preparedStatement.setString(1, empLoginId);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String employeeId = resultSet.getString("employee_id");
                    String empName = resultSet.getString("emp_name");
                    employee = new Employee(employeeId, empName, empLoginId, password);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Employee findById(String employeeId) {
        Employee employee = null;
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID_QUERY)) {
            preparedStatement.setString(1, employeeId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee(rs.getString("employee_id"),
                            rs.getString("emp_name"),
                            rs.getString("emp_login_id"),
                            rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void deleteEmp(String employeeId) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            preparedStatement.setString(1, employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Policy getDetails(int policyId, int userId) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_POLICY_DETAILS_QUERY)) {
            statement.setInt(1, policyId);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Policy policy = new Policy();
                    policy.setInsuranceCode(resultSet.getInt("PolicyID"));
                    policy.setPolicyName(resultSet.getString("PolicyName"));
                    policy.setPolicyType(resultSet.getString("PolicyType"));
                    policy.setTenure(resultSet.getInt("Tenure"));
                    policy.setPrice(resultSet.getDouble("PolicyPrice"));
                    policy.setCoverage(resultSet.getInt("Coverage"));
                    policy.setStartDate(resultSet.getDate("StartDate").toLocalDate());
                    LocalDate startDate = resultSet.getDate("StartDate").toLocalDate();
                    LocalDate endDate = startDate.plusYears(resultSet.getInt("Tenure"));
                    policy.setEndDate(endDate);
                    return policy;
                } else {
                    return null; // Policy not found
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePremiumAmount(int policyID, double newAmount) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PREMIUM_AMOUNT_QUERY)) {
            statement.setDouble(1, newAmount);
            statement.setInt(2, policyID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update premium amount: " + e.getMessage(), e);
        }
    }

    public boolean updatePolicyClosureDate(int policyID, String closureDate) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_POLICY_CLOSURE_DATE_QUERY)) {
            statement.setString(1, closureDate);
            statement.setInt(2, policyID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean handOverPolicy(int policyID) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(HAND_OVER_POLICY_QUERY)) {
            statement.setInt(1, policyID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
