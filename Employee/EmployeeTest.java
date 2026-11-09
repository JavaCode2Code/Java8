package com.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeTest {
    public static List<Employee> eligbleForTax(String in){
        return (in.equalsIgnoreCase("tax")) ? DataBase.getEmployeeList().stream().filter(emp->emp.getSalary()> 70000).collect(Collectors.toList()):
                DataBase.getEmployeeList().stream().filter(emp->emp.getSalary()< 70000).collect(Collectors.toList());
    }
    public static void main(String[] args) {
   System.out.println(eligbleForTax("non tax"));
        Map<String, Optional<Employee>> map= DataBase.getEmployeeList().stream().collect(Collectors.groupingBy(Employee::getDept,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
     map.forEach((dept,emp)->{
         System.out.println(dept + ":" + emp.get().getName() + " " + emp.get().getSalary());
     });


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
    }
}
