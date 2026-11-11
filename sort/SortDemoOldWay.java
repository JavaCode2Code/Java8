package com.sort;

import com.Employee.DataBase;
import com.Employee.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortDemoOldWay {

    public static void main(String[] args) {
        class Mycomarator implements Comparator<Employee> {
            @Override
            public int compare(Employee o1, Employee o2) {
                //return (int) (o1.getSalary() - o2.getSalary());
                return (int) (o2.getSalary() - o1.getSalary());

            }
        }
        List<Integer> list = new ArrayList<>();


        list.add(8);
        list.add(3);
        list.add(12);
        list.add(4);

        List<Employee> listEmployee = DataBase.getEmployeeList();
        // Collections.sort(listEmployee, new Mycomarator());
        //   Collections.sort(list);
        //  Collections.reverse(listEmployee);

        System.out.println(list);
    }
}