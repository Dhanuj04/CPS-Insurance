package com.insurance.dao;

import com.insurance.connections.DatabaseConnection;
import com.insurance.contracts.IInsuranceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsuranceDAO implements IInsuranceDAO {

    private static final String INSERT_INSURANCE_QUERY = "INSERT INTO Insurance (PolicyID, PolicyName, PolicyType, Tenure, PolicyPrice, Coverage, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public void createNewInsurance(int insuranceCode, String title, String policy, int tenure, double price, int coverage, String status) {
        try (Connection connection = DatabaseConnection.getMySqlConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_INSURANCE_QUERY)) {
            statement.setInt(1, insuranceCode);
            statement.setString(2, title);
            statement.setString(3, policy);
            statement.setInt(4, tenure);
            statement.setDouble(5, price);
            statement.setInt(6, coverage);
            statement.setString(7, status);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thank you, insurance created successfully.");
            } else {
                System.out.println("Failed to create insurance.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
