package com.sort;

import com.Employee.DataBase;
import com.Employee.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortDemoMordenWay {
    public static void main(String[] args) {
        List<Employee> listEmployee = DataBase.getEmployeeList();

        //assending lambda expression to sort the list of employee based on salary

        //Collections.sort(listEmployee,(o1,o2)->(int)(o1.getSalary()-o2.getSalary()));


        //descending lambda expression to sort the list of employee based on salary
        //  Collections.sort(listEmployee,(o1,o2)->(int)(o2.getSalary()-o1.getSalary()));


        //Stream Api to sort the list of employee based on salary
       // listEmployee.stream().sorted((o1, o2) -> (int) (o1.getSalary() - o2.getSalary())).forEach(System.out::println);

        //getSalary
      // listEmployee.stream().sorted(Comparator.comparing(emp->emp.getSalary())).forEach(System.out::println);

        //getDept
      // listEmployee.stream().sorted(Comparator.comparing(Employee::getDept)).forEach(System.out::println);

        Stream<Employee> listEmployees = listEmployee.stream().sorted(Comparator.comparing(Employee::getDept));

        listEmployee.stream().sorted(Comparator.comparing(Employee::getName));
     //   System.out.println(listEmployees.collect(Collectors.toList()));

        List<Integer> list = new ArrayList<>();


        list.add(8);
        list.add(3);
        list.add(12);
        list.add(4);
        System.out.println(list);
        list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

    }

}
