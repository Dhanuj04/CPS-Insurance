package com.insurance.controllers;

import com.insurance.service.InsuranceService;
import com.insurance.service.EmployeeService;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {

    // Private constructor to hide the implicit public one
    private AdminController() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    static Scanner scanner = new Scanner(System.in);

    public static void handleAdminLogin(EmployeeService employeeService) throws SQLException {
        System.out.println("Enter admin username:");
        String adminUsername = scanner.next();

        System.out.println("Enter admin password:");
        String adminPassword = scanner.next();

        if ("admin".equals(adminUsername) && "adminpassword".equals(adminPassword)) {
            boolean isAdminLoggedIn = true;
            while (isAdminLoggedIn) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. Create Employee");
                System.out.println("2. Disable Employee");
                System.out.println("3. Create Insurance");
                System.out.println("4. Back");
                System.out.print("Enter your choice: ");

                int adminChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character after reading the integer

                switch (adminChoice) {
                    case 1 -> employeeService.createEmployee();
                    case 2 -> {
                        System.out.println("Enter EmployeeID:");
                        employeeService.deleteEmployee(scanner.nextLine().trim());
                    }
                    case 3 -> {
                        InsuranceService insuranceService = new InsuranceService();
                        insuranceService.createInsurance();
                    }
                    case 4 -> isAdminLoggedIn = false;
                    default -> System.out.println("Invalid option. Please choose again.");
                }
            }
        } else {
            System.out.println("Invalid admin credentials. Please try again.");
        }
    }
}
