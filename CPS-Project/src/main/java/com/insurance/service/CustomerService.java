package com.insurance.service;

import com.insurance.connections.DatabaseConnection;
import com.insurance.contracts.ICustomerService;
import com.insurance.dao.CustomerDAO;
import com.insurance.entity.Customer;
import com.insurance.entity.Insurance;
import com.insurance.entity.Order;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerService implements ICustomerService {

    private final CustomerDAO customerDAO = new CustomerDAO();

    public void registerNewCustomer(String name, String email, String password, String dob, String city, String state) throws SQLException {
        Random random = new Random();
        int customerCode = random.nextInt(9000000) + 1000000;
        Customer customer = new Customer();
        customer.setUserId(customerCode);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setDob(LocalDate.parse(dob));
        customer.setCity(city);
        customer.setState(state);
        customer.setCredits(100000);
        boolean success = customerDAO.insertCustomer(customer);
        if (success) {
            System.out.println("Your registration was successful! \nYour Customer-ID is " + customerCode);
        } else {
            System.out.println("Failed to register. Please try again later.");
        }
    }

    public void updateCustomer(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the field you want to update:");
        System.out.println("1. Name\n2. Email\n3. Password\n4. DOB\n5. City\n6. State\n7. Amount");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        String columnName;
        switch (choice) {
            case 1 -> columnName = "name";
            case 2 -> columnName = "email";
            case 3 -> columnName = "password";
            case 4 -> columnName = "dob";
            case 5 -> columnName = "city";
            case 6 -> columnName = "state";
            case 7 -> columnName = "amount";
            default -> {
                System.out.println("Invalid choice");
                return;
            }
        }

        System.out.print("Enter the new value for " + columnName + ": ");
        String newValue = scanner.next();
        customerDAO.updateCustomer(email, newValue, columnName);
    }

    public void viewAvailableInsurance() {
        List<Insurance> availableInsurances = customerDAO.getAvailableInsurances();
        if (availableInsurances.isEmpty()) {
            System.out.println("No available insurances.");
        } else {
            System.out.println("Available Insurances:");
            availableInsurances.forEach(insurance -> {
                System.out.println("Insurance Code: " + insurance.getInsuranceCode()+ " | Title: " + insurance.getPolicyName()+ " | Policy: " + insurance.getPolicyType()+ " | Tenure: " + insurance.getTenure()+ " | Price: " + insurance.getPrice()+ " | Coverage: " + insurance.getCoverage() + " | Status: " + insurance.getStatus() + " | Benefits: "+insurance.getBenefits());
            });
            Random random = new Random();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Insurance - Id:");
            int a = scanner.nextInt();
            System.out.println("Enter the Customer - Id:");
            int b = scanner.nextInt();
            int c = random.nextInt(9000000) + 1000000;

            customerDAO.createOrder(a, b, c);
        }

//        Random random = new Random();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Enter the Insurance - Id:");
//        int a = scanner.nextInt();
//        System.out.println("Enter the Customer - Id:");
//        int b = scanner.nextInt();
//        int c = random.nextInt(9000000) + 1000000;
//
//        customerDAO.createOrder(a, b, c);
    }

    public List<Order> viewRegisteredInsurance() {
        return customerDAO.registeredInsurances();
    }

    public void updatePaymentStatus(int orderId, int userId, double policyPrice) throws SQLException {
        customerDAO.updateStatus(orderId, userId, policyPrice);
    }

    public String validateLogin(String email, String password) throws SQLException {
        return customerDAO.validateLogin(email,password);
    }
}
