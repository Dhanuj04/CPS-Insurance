package com.insurance.contracts;

public interface IInsuranceDAO {

    public void createNewInsurance(int insuranceCode, String title, String policy, int tenure, double price, int coverage, String status);
}
