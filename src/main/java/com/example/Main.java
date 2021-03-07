package com.example;

import com.example.Entities.Employee;
import com.example.Entities.EmployeeType;
import com.example.Service.SalaryCalculatorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

@Controller
public class Main {
    Scanner scan = new Scanner(System.in);
    @Autowired
    SalaryCalculatorServiceInterface salaryCalculatorServiceInterface;
    private static DecimalFormat decimalFormat = new DecimalFormat("");
    @PostConstruct
    public void showMenu() {

        int choice = 0;
        while (choice != 6) {
            int i = 0;
            System.out.println("1. Calculate");
            System.out.println("2. List");
            System.out.println("3. Change Commission Rate");
            System.out.println("4. Change salary of employee");
            System.out.println("5. Change Hour Rate");
            System.out.println("6. Exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Choose one: ");
                    System.out.println("1.Salaried ");
                    System.out.println("2.Hourly ");
                    System.out.println("3.Comission Emp ");
                    System.out.println("4.Salaried comission Emp ");

                    Scanner sc = new Scanner(System.in);
                    int x = sc.nextInt();

                    if (x == 1){
                        List<Employee> employees = salaryCalculatorServiceInterface.getByType(EmployeeType.SALARIED);
                        for (Employee employee: employees) {
                            System.out.println(  employee.getName() + ": " + employee.getFixedSalary());
                            i++;
                        }
                    } else if (x == 2){
                        List<Employee> employees;
                        employees = salaryCalculatorServiceInterface.getByType(EmployeeType.HOURLY);
                        for (Employee employee: employees) {
                            double salary = employee.getFixedSalary();
                            int hours = employee.getHoursWorked();
                            if (hours > 40) {
                                System.out.println( employee.getName() + ": " + (salary * 40 + (hours - 40) * salary * employee.getHourRate()));
                            }
                            else {
                                System.out.println( employee.getName() + ": " + salary * hours);
                            }
                            i++;
                        }
                    } else if (x == 3){
                        List<Employee> employees;
                        employees = salaryCalculatorServiceInterface.getByType(EmployeeType.COMMISSION);

                        for (Employee employee: employees) {
                            double salary = (employee.getFixedSalary() * employee.getCommRate());
                            System.out.println( employee.getName() + ": " + decimalFormat.format(salary));
                            i++;
                        }
                    } else if (x == 4){
                        List<Employee> employees;
                        employees = salaryCalculatorServiceInterface.getByType(EmployeeType.SALARIED_COMMISSION);
                        for (Employee employee: employees) {
                            double salary = ((employee.getFixedSalary() + employee.getCommRate() * employee.getFixedSalary()));
                            System.out.println( employee.getName() + ": " + decimalFormat.format(salary));
                            i++;
                        }
                    }


                }

                break;

                case 2: {
                    List<Employee> employees = salaryCalculatorServiceInterface.findAll();
                    for (Employee employee : employees) {
                        String name = employee.getName();
                        long id = employee.getId();
                        System.out.println("id:" + id + " " + name + " Type : " + employee.getEmplType());
                        System.out.println();
                    }
                }
                break;

                case 3: {
                    System.out.println("id: ");
                    int id = scan.nextInt();
                    System.out.println("New commission rate: ");
                    float rate = scan.nextFloat();
                    salaryCalculatorServiceInterface.changeCommRate(id,rate);

                }
                break;

                case 4: {

                    System.out.println("id: ");
                    int id = scan.nextInt();
                    System.out.println("New salary: ");
                    double salary = scan.nextDouble();
                    salaryCalculatorServiceInterface.changeSalary(id,salary);
                }
                break;

                case 5: { System.out.println("id: ");
                    int id = scan.nextInt();
                    System.out.println("New hour rate: ");
                    double rate = scan.nextDouble();
                    salaryCalculatorServiceInterface.changeHourRate(id,rate);
                }
                break;
                default: {

                }
            }
        }
    }
}
