package com.example.Event;

import com.example.Entities.Employee;
import org.springframework.context.ApplicationEvent;

public class SalaryChangeEvent extends ApplicationEvent {
    Employee employee;
    public SalaryChangeEvent(Object s, Employee e) {
        super(s);
        this.employee = e;
    }

}
