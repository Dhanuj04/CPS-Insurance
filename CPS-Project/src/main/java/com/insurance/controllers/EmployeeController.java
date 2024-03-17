package com.insurance.controllers;

import com.insurance.dao.EmployeeDAO;
import com.insurance.entity.Employee;
import com.insurance.entity.Policy;
import com.insurance.service.EmployeeService;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeController {


    private EmployeeController() {
        throw new IllegalStateException("Utility class");
    }

    static Scanner scanner = new Scanner(System.in);

    public static void handleEmployeeLogin(EmployeeDAO employeeDAO) throws SQLException {
        System.out.println("Enter employee login ID:");
        String empLoginId = scanner.next();

        System.out.println("Enter password:");
        String password = scanner.next();

        Employee employee = employeeDAO.getByLoginIdAndPassword(empLoginId, password);

        if (employee != null) {
            System.out.println("Login successful ! Welcome " + employee.getEmpName());
            showEmployeeOptions(employee);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void showEmployeeOptions(Employee employee) throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        while (true) {
            System.out.println("Employee Options:");
            System.out.println("1. View policy");
            System.out.println("2. Update policy");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");

            int choice = EmployeeController.scanner.nextInt();
            EmployeeController.scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewPolicyDetails(employeeService);
                    break;
                case 2:
                    updatePolicy(employeeService);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewPolicyDetails(EmployeeService employeeService) throws SQLException {
        System.out.println("Enter User Id : ");
        int userId = scanner.nextInt();
        System.out.println("Enter Insurance Id : ");
        int insuranceId = scanner.nextInt();
        Policy policy = employeeService.getPolicyDetails(insuranceId, userId);
        if (policy != null) {
            System.out.println(policy); // Using toString method of Policy class
        } else {
            System.out.println("Policy not found for the specified ID.");
        }
    }

    private static void updatePolicy(EmployeeService employeeService) {
        while (true) {
            System.out.println("Employee Update Options:");
            System.out.println("1. Update Premium");
            System.out.println("2. Policy Pre-Closure");
            System.out.println("3. Policy HandOver");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    updatePremium(employeeService);
                    break;
                case 2:
                    updatePolicyPreClosure(employeeService);
                    break;
                case 3:
                    closePolicy(employeeService);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void updatePremium(EmployeeService employeeService) {
        System.out.println("Enter User Id : ");
        int userId = scanner.nextInt();
        System.out.println("Enter Insurance Id : ");
        int insuranceId = scanner.nextInt();
        System.out.println("Enter new premium amount: ");
        double newAmount = scanner.nextDouble();
        boolean success = employeeService.updatePremium(insuranceId, newAmount);
        if (success) {
            System.out.println("Premium amount updated successfully.");
        } else {
            System.out.println("Failed to update premium amount.");
        }
    }

    private static void updatePolicyPreClosure(EmployeeService employeeService) {
        System.out.println("Enter Insurance Id : ");
        int insuranceId = scanner.nextInt();
        System.out.println("Enter the closure date (YYYY-MM-DD):");
        String closureDate = scanner.next();
        try {
            boolean success = employeeService.updatePolicyPreClosure(insuranceId, closureDate);
            if (success) {
                System.out.println("Policy pre-closure date updated successfully.");
            } else {
                System.out.println("Failed to update policy pre-closure date.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closePolicy(EmployeeService employeeService) {
        System.out.println("Enter Insurance Id : ");
        int insuranceId = scanner.nextInt();
        try {
            boolean success = employeeService.closePolicy(insuranceId);
            if (success) {
                System.out.println("Policy pre-closure date updated successfully.");
            } else {
                System.out.println("Failed to update policy pre-closure date.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
