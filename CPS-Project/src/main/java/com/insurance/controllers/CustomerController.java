package com.insurance.controllers;

import com.insurance.entity.Order;
import com.insurance.service.CustomerService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CustomerController {

    private CustomerController() {
        // Private constructor to hide the implicit public one
        throw new IllegalStateException("Utility class");
    }

    static Scanner scanner = new Scanner(System.in);


    public static void handleCustomerLogin() {
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.print("Enter your email: ");
            String email = scanner.next();
            System.out.print("Enter your password: ");
            String password = scanner.next();
            CustomerService customerService = new CustomerService();
            try {
                String username = customerService.validateLogin(email, password);
                if (username != null) {
                    isLoggedIn = true;
                    handleCustomerOptions(customerService,email, username);
                } else {
                    isLoggedIn = handleInvalidCredentials();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void handleCustomerOptions(CustomerService customerService,String email, String name) throws SQLException {
        System.out.println("Welcome | "+name);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. View Available insurance");
            System.out.println("2. View Enrolled Insurance");
            System.out.println("3. Update Profile");
            System.out.println("4. Back to Main Menu");

            int customerOption = scanner.nextInt();

            switch (customerOption) {
                case 1:
                    customerService.viewAvailableInsurance();
                    break;
                case 2:
                    viewRegisteredInsurance(customerService);
                    break;
                case 3:
                    customerService.updateCustomer(email);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static boolean handleInvalidCredentials() {
        System.out.println("Invalid Credentials");
        System.out.println("Select an option:");
        System.out.println("1. Re-enter details");
        System.out.println("2. Back to Main Menu");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return false; // Continue the loop to re-enter details
            case 2:
                return true; // Exit the loop and go back to the main menu
            default:
                System.out.println("Invalid option");
                return false; // Continue the loop
        }
    }

    public static void registerNewCustomer(CustomerService customerService) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String name = getName(scanner);
        String email = getEmail(scanner);
        String password = getPassword(scanner);
        String dob = getValidDob(scanner);
        String city = getValidCity(scanner);
        String state = getValidState(scanner);

        customerService.registerNewCustomer(name, email, password, dob, city, state);
    }

    private static String getName(Scanner scanner) {
        System.out.println("User Name:");
        return scanner.next();
    }

    private static String getEmail(Scanner scanner) {
        String email;
        boolean validEmail = false;
        do {
            System.out.println("User Email:");
            email = scanner.next();
            if (isValidEmail(email)) {
                validEmail = true;
            } else {
                System.out.println("Invalid Email. Please enter a valid email address.");
            }
        } while (!validEmail);
        return email;
    }

    private static String getPassword(Scanner scanner) {
        String password;
        boolean validPassword = false;
        do {
            System.out.println("Password (at least 8 characters with one special character and one number):");
            password = scanner.next();
            if (isValidPassword(password)) {
                validPassword = true;
            } else {
                System.out.println("Invalid Password. Please enter a valid password.");
            }
        } while (!validPassword);
        return password;
    }

    private static String getValidDob(Scanner scanner) {
        String dob;
        boolean validDob = false;
        do {
            System.out.println("Date of birth (YYYY-MM-DD):");
            dob = scanner.next();
            if (isValidDob(dob)) {
                validDob = true;
            } else {
                System.out.println("Invalid Date Format | Age less than 18 ");
            }
        } while (!validDob);
        return dob;
    }

    private static String getValidCity(Scanner scanner) {
        String city;
        do {
            System.out.println("City:");
            city = scanner.next();
            if (!isValidCity(city)) {
                System.out.println("Invalid City. Please enter a non-empty city name.");
            }
        } while (!isValidCity(city));
        return city;
    }

    private static String getValidState(Scanner scanner) {
        String state;
        boolean validState = false;
        do {
            System.out.println("State:");
            state = scanner.next();
            if (isValidState(state)) {
                validState = true;
            } else {
                System.out.println("Invalid State. Please enter a state in India.");
            }
        } while (!validState);
        return state;
    }



    // Helper methods for validation
    public static boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w.-]+@([\\w-]+\\.)+[\\w]{2,}$", email);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[!@#$%^&*()])(?=.*[a-zA-Z]).{8,}$", password);
    }

    public static boolean isValidDob(String dob) {
        try {
            LocalDate birthDate = LocalDate.parse(dob);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            int age = period.getYears();
            return age >= 18;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidState(String state) {
        return !state.trim().isEmpty();
    }

    public static boolean isValidCity(String city) {
        return !city.trim().isEmpty();
    }

    private static void viewRegisteredInsurance(CustomerService customerService) throws SQLException {
        List<Order> registeredInsurances = customerService.viewRegisteredInsurance();

        if (registeredInsurances.isEmpty()) {
            System.out.println("No insurances are registered.");
            return;
        }

        float totalPolicyPrice = 0;
        for (Order order : registeredInsurances) {
            System.out.println(order);
            totalPolicyPrice += order.getPolicyPrice();
        }

        System.out.println("Payment");
        String q = scanner.next();
        if (q.equalsIgnoreCase("Yes")) {
            for (Order order : registeredInsurances) {
                customerService.updatePaymentStatus(order.getOrderID(), order.getUserID(), order.getPolicyPrice());
            }
            System.out.println("Thank you for paying Insurance amount of " + totalPolicyPrice);
        } else {
            return;
        }
    }

}
