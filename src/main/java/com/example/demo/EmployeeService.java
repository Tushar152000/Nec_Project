package com.example.demo;

import java.util.List;

public interface EmployeeService {
    List < Employee > getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Employee authenticateEmployee(String username, String password);
    

}