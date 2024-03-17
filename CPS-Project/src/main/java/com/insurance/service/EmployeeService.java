package com.insurance.service;

import com.insurance.contracts.IEmployeeService;
import com.insurance.dao.EmployeeDAO;
import com.insurance.entity.Employee;
import com.insurance.entity.Policy;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeService implements IEmployeeService {
    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public void createEmployee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter employee ID:");
        String employeeId = scanner.nextLine();

        System.out.println("Enter employee name:");
        String empName = scanner.nextLine();

        System.out.println("Enter employee login ID:");
        String empLoginId = scanner.nextLine();

        System.out.println("Enter initial password:");
        String password = scanner.nextLine();

        Employee employee = new Employee(employeeId, empName, empLoginId, password);
        employeeDAO.createEmployee(employee);
        System.out.println("Employee created successfully");
    }

    @Override
    public void deleteEmployee(String employeeId) {
        if (employeeId != null && !employeeId.isEmpty()) {
            employeeDAO.deleteEmp(employeeId);
            System.out.println("Employee with ID " + employeeId + " has been deleted from the database.");
        } else {
            System.out.println("Invalid employee ID.");
        }
    }

    public Policy getPolicyDetails(int policyId, int userId) throws SQLException {
        return employeeDAO.getDetails(policyId, userId);
    }

    public boolean updatePremium(int policyID, double newAmount) {
        return employeeDAO.updatePremiumAmount(policyID, newAmount);
    }

    public boolean updatePolicyPreClosure(int policyID, String closureDate) {
        // Check if closure date is today or in the future
        // Implement your logic to validate the closure date here

        // Update policy closure date
        return employeeDAO.updatePolicyClosureDate(policyID, closureDate);
    }

    public boolean closePolicy(int policyID) throws SQLException {
        return employeeDAO.handOverPolicy(policyID);
    }
}
