package com.debugger;

import java.util.List;
import java.util.stream.Collectors;

public class StreamDebugger {
    public static void main(String[] args) {
        List<Employee> empList = EmployeeDatabase.getEmployees();
        List<String> names = empList.stream().filter(emp -> emp.getSalary() > 40000).
        map(employee -> employee.getName()).
        distinct().
        sorted().
        collect(Collectors.toList());

        System.out.println(names);
    }
}
