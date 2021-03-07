package com.example.Service;

import com.example.Entities.Employee;
import com.example.Entities.EmployeeType;
import com.example.EmployeeRepository;
import com.example.Event.SalaryChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryCalculatorService  implements SalaryCalculatorServiceInterface, ApplicationEventPublisherAware {
    @Autowired
    private EmployeeRepository employeeRepository;
    private ApplicationEventPublisher eventPublisher;
    @Override
    public void create(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getByType(EmployeeType employeeType) {
        return employeeRepository.findEmployeesByEmplType(employeeType);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.findEmployeesById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void changeSalary(long id, double salary) {
        Employee employee = findById(id);
        employee.setFixedSalary(salary);
        employeeRepository.save(employee);
        this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employee));
    }
    @Override
    public void changeHourRate(long id, double rate) {
        Employee employee = findById(id);
        employee.setHourRate(rate);
        employeeRepository.save(employee);
        this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employee));
    }
    @Override
    public void changeCommRate(long id, float rate) {
        Employee employee = findById(id);
        employee.setCommRate(rate);
        employeeRepository.save(employee);
        this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employee));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
