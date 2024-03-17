package com.insurance.service;

import com.insurance.contracts.IInsuranceService;
import com.insurance.dao.InsuranceDAO;

import java.util.Scanner;

public class InsuranceService implements IInsuranceService {

    public void createInsurance() {
        try (Scanner scanner = new Scanner(System.in)) {
            // Get insurance details from admin
            System.out.print("Enter Policy ID: ");
            int insuranceCode = scanner.nextInt();
            System.out.print("Enter Policy Name: ");
            String title = scanner.next();
            System.out.print("Enter Policy Type: ");
            String policy = scanner.next();
            System.out.print("Enter tenure: ");
            int tenure = scanner.nextInt();
            System.out.print("Enter Policy Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter Coverage: ");
            int coverage = scanner.nextInt();
            System.out.print("Enter Status: ");
            String status = scanner.next();

            InsuranceDAO insuranceDAO = new InsuranceDAO();
            insuranceDAO.createNewInsurance(insuranceCode, title, policy, tenure, price, coverage, status);
        }
    }
}
