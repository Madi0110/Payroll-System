package com.example.Service;

import com.example.Entities.Employee;
import com.example.Entities.EmployeeType;

import java.util.List;

public interface SalaryCalculatorServiceInterface {
    void create(Employee employee);
    List<Employee> findAll();
    List<Employee> getByType(EmployeeType employeeType);
    Employee findById(long id);
    void save(Employee employee);
    void changeSalary(long id, double salary);
    void changeHourRate(long id, double rate);
    void changeCommRate(long id, float rate);
}
