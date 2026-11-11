package com.sort;

import com.Employee.Employee;
import com.Employee.Project;

import java.util.*;

public class SortedMapOldWay {
    public static void main(String[] args) {


       //traditinal way to sort the map based on key
        Map<Integer, String> map = new HashMap();
        map.put(1, "Samsung");
        map.put(2, "Sony");
        map.put(3, "Apple");
        map.put(5, "OnePlus");
        map.put(6, "Blackberry");
        map.put(7, "LG");
        map.put(8, "Lenovo");
        List<Map.Entry<Integer, String>> entries = new ArrayList<>(map.entrySet());




      /*  Collections.sort(entries, new Comparator<Map.Entry<Integer, String>>() {

            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });*/



       /* for (Map.Entry<Integer, String> entry : entries) {

            System.out.println(entry.getKey() + "  " + entry.getValue());
        }*/



     /* Collections.sort(entries, new Comparator<Map.Entry<Integer, String>>() {

            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });



        for (Map.Entry<Integer, String> entry : entries) {

            System.out.println(entry.getKey() + "  " + entry.getValue());
        }*/



        Map<Employee, Integer> mapEmployee = new TreeMap<>(new Comparator<Employee>() {

            @Override
            public int compare(Employee o1, Employee o2) {
                return (int) (o2.getSalary() - o1.getSalary());

            }
        });



        Project p1 = new Project("P001", "Alpha", "ABC Corp", "Alice");
        Project p2 = new Project("P002", "Beta", "XYZ Ltd", "Bob");
        Project p3 = new Project("P003", "Gamma", "ABC Corp", "Alice");
        Project p4 = new Project("P004", "Delta", "TechWorld", "Charlie");
        Employee e1 = new Employee(1, "John Doe", "Development", Arrays.asList(p1, p2), 80000, "Male");
        Employee e2 = new Employee(2, "Jane Smith", "Development", Arrays.asList(p3), 80000, "Female");
        Employee e3 = new Employee(3, "Robert Brown", "Sales", Arrays.asList(p4), 60000, "Male");
        Employee e4 = new Employee(4, "Lisa White", "HR", Arrays.asList(p1), 55000, "Female");


        mapEmployee.put(e1, 60);
        mapEmployee.put(e2, 90);
        mapEmployee.put(e3, 100);
        mapEmployee.put(e4, 110);
        System.out.println(mapEmployee);



    }
}
