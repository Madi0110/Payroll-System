package com.example;

import com.example.Entities.Employee;
import com.example.Entities.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeesByEmplType(EmployeeType employeeType);
    Employee findEmployeesById(long id);
    List<Employee> findAll();

}
