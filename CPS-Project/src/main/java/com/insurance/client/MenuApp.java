package com.insurance.client;
import com.insurance.dao.EmployeeDAO;
import com.insurance.service.CustomerService;
import com.insurance.service.EmployeeService;
import java.sql.SQLException;
import java.util.Scanner;
import static com.insurance.controllers.AdminController.handleAdminLogin;
import static com.insurance.controllers.CustomerController.handleCustomerLogin;
import static com.insurance.controllers.CustomerController.registerNewCustomer;
import static com.insurance.controllers.EmployeeController.handleEmployeeLogin;

public class MenuApp {

    public static void main(String[] args) throws SQLException {


        CustomerService customerService = new CustomerService();

        try (Scanner scanner = new Scanner(System.in)) {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            EmployeeService employeeService = new EmployeeService();
            boolean exit = false;

            while (!exit) {
                System.out.println("Main Menu:");
                System.out.println("1. Customer Login");
                System.out.println("2. Admin Login");
                System.out.println("3. Employee Login");
                System.out.println("4. Exit");

                int option = scanner.nextInt();

                switch (option) {
                    case 1 -> {
                        boolean validOptionSelected = false;
                        while (!validOptionSelected) {
                            System.out.println("Choose an option:");
                            System.out.println("1. Customer Login");
                            System.out.println("2. Register New Customer");
                            System.out.print("Enter your choice: ");

                            if (scanner.hasNextInt()) { // Check if the input is an integer
                                int choice = scanner.nextInt();

                                switch (choice) {
                                    case 1:
                                        handleCustomerLogin();
                                        validOptionSelected = true;
                                        break;
                                    case 2:
                                        registerNewCustomer(customerService);
                                        validOptionSelected = true;
                                        break;
                                    default:
                                        System.out.println("Invalid Option");
                                        System.out.println("Please choose between options 1 and 2.");
                                        break;
                                }
                            } else {
                                System.out.println("Invalid Option");
                                System.out.println("Please choose between options 1 and 2.");
                                scanner.next(); // Consume the invalid input
                            }
                        }
                    }

                    case 2 -> handleAdminLogin(employeeService);
                    case 3 -> handleEmployeeLogin(employeeDAO);
                    case 4 -> exit = true;
                    default -> System.out.println("Invalid option! Choose between 1 to 4");
                }
            }
        }
    }
}
