package com.insurance.contracts;

import com.insurance.entity.Employee;

import java.sql.SQLException;

public interface IEmployeeDAO {

    public void createEmployee(Employee employee);

    public Employee getById(String employeeId) throws SQLException;

    public Employee getByLoginIdAndPassword(String empLoginId, String password) throws SQLException;

    Employee findById(String employeeId);
    void deleteEmp(String employeeId);
}
