package com.insurance.contracts;

import com.insurance.connections.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public interface IInsuranceService {

    public void createInsurance() throws SQLException;
}
