package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//        forEach(Consumer)
//        filter(Predicate)
//        collect(Collector)
//        map(Function)
//        distinct()
//        flatMap(Function)
//        sorted(Comparator both ASC and DESC)
//        min() & max()
//        GroupBy
//        findFirst()
//        findAny()
//        anyMatch(Predicate)
//        allMatch(Predicate)
//        noneMatch(Predicate)
//        limit(long maxSize)
//        skip(long n)
public class EmployeeTest {
    public static void main(String[] args) {
        List<Employee> allEmployees = EmployeeDataBase.getAllEmployees();
        //forEach
        //allEmployees.forEach(System.out::println);


        //filter
        // allEmployees.stream().filter(emp -> emp.getSalary() > 70000).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("***************************");
        //Belong to Development
        allEmployees.stream().filter(emp -> emp.getDept().equalsIgnoreCase
                ("Development")).collect(Collectors.toSet()).forEach(System.out::println);


        System.out.println("*********Development***map***************");
        //Belong to Development get Name
        allEmployees.stream().filter(emp -> emp.getDept().equalsIgnoreCase("Development"))
                .map(Employee::getName).
                toList().forEach(System.out::println);

        System.out.println("***********Development toMap****************");

        //Belong to Development map Id and  Name using toMap
        allEmployees.stream().filter(emp -> emp.getDept()
                        .equalsIgnoreCase("Development"))
                .collect(Collectors.toMap(Employee::getId, Employee::getName)).entrySet()
                .forEach(System.out::println);

        System.out.println("***************************");

        //map
        List<String> mapD = allEmployees.stream().map(Employee::getDept).distinct().collect(Collectors.toList());
        System.out.println(mapD);

        System.out.println("***************************");
        //flatmap
        Set<String> projects = allEmployees.stream().flatMap(emp -> emp.getProjects().stream()).map(Project::getName).collect(Collectors.toSet());
        System.out.println(projects);

        System.out.println("***************************");

        //sorted asc order
        List<Employee> ascSortBySalary = allEmployees.stream().sorted(Comparator.comparing(Employee::getSalary)).toList();
        System.out.println(ascSortBySalary);

        System.out.println("*******************************************");


        //sorted dsc order
        List<Employee> dscOrderlist = allEmployees.stream().sorted(Collections.reverseOrder(Comparator.comparing(Employee::getSalary))).toList();
        System.out.println(dscOrderlist);

        //min and max
        System.out.println("*******************************************");
        List<Employee> maxSal = allEmployees.stream().max(Comparator.comparing(Employee::getSalary)).stream().toList();
        System.out.println(maxSal);
        //min
        System.out.println("*******************************************");
        List<Employee> minsal = allEmployees.stream().min(Comparator.comparing(Employee::getSalary)).stream().toList();
        System.out.println(minsal);
        System.out.println("*******************************************");

        //groupingBy
        Map<String, List<Employee>> groupEmpByGeder = allEmployees.stream().collect(Collectors.groupingBy(Employee::getGender));
        System.out.println(groupEmpByGeder);
        System.out.println("*******************************************");

        //gender:[ Name]

        Map<String, List<String>> groupNameByGender = allEmployees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.mapping(Employee::getName, Collectors.toList())));
      //  System.out.println(groupNameByGender);
        System.out.println("****************Gender: [Count]***************************");

        //Gender: [Count]
        Map<String, Long> countBygender = allEmployees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
      //  System.out.println(countBygender);

        //findFirst
        System.out.println("********************findFirst***********************");
       // Employee findfirst = allEmployees.stream().findFirst().orElse(new Employee());
        Employee findfirst = allEmployees.stream().findFirst().orElseThrow(IllegalArgumentException::new);

        System.out.println(findfirst);
        System.out.println("********************anyMatch(Predicate)***********************");

        //allMatch(Predicate) ,anyMatch(Predicate) ,noneMatch(Predicate)
        boolean anyMatch = allEmployees.stream().anyMatch(emp -> "ajjj".equals(emp.getDept()));
        System.out.println(anyMatch);

        System.out.println("********************allMatch(Predicate)***********************");
        boolean allMatch = allEmployees.stream().allMatch(emp -> "Development".equals(emp.getDept()));
        System.out.println(allMatch);

        System.out.println("********************noneMatch(Predicate)***********************");
        boolean nonMatch = allEmployees.stream().noneMatch((emp -> "Development".equals(emp.getDept())));
        System.out.println(nonMatch);

        System.out.println("********************limit(long)***********************");

        //limit(long)
        List<Employee> empLimit = allEmployees.stream().sorted(Comparator.comparing(Employee::getName).reversed()).limit(10).collect(Collectors.toList());
        empLimit.forEach(e->System.out.println(e.getName()));

    }

}
