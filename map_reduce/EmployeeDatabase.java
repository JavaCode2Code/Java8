package com.map_reduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDatabase {


    public static List<Employee> getEmployees() {
        return Stream.of(new Employee(101, "john", "A", 60000),
                        new Employee(109, "peter", "B", 30000),
                        new Employee(102, "mak", "A", 80000),
                        new Employee(103, "kim", "A", 90000),
                        new Employee(104, "json", "C", 15000))
                .collect(Collectors.toList());
    }

    public static List<Employee> getParallelEmployees() {
        List<Employee> list = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            list.add(new Employee(i, "john", "A", Double.valueOf(new Random().nextInt(100 * 100))));
        }
        return list;
    }
}
