package org.example;

import java.util.Arrays;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        return Arrays.asList(
                new Employee(1, "Alice", "HR", 50000, 28),
                new Employee(2, "Bob", "IT", 75000, 35),
                new Employee(3, "Charlie", "Finance", 60000, 40),
                new Employee(4, "David", "IT", 55000, 32),
                new Employee(5, "Eve", "HR", 52000, 26),
                new Employee(6, "Frank", "Finance", 70000, 50),
                new Employee(7, "Grace", "IT", 62000, 29),
                new Employee(8, "Hank", "Finance", 80000, 45),
                new Employee(9, "Ivy", "HR", 53000, 30)
        );
    }
}

