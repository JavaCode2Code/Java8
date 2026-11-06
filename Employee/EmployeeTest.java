package com.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    }
}
