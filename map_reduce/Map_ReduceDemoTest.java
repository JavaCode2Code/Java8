package com.map_reduce;

import java.util.Arrays;
import java.util.List;

public class Map_ReduceDemoTest {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5);
        List<String> strList = Arrays.asList("Language", "Manufacturing", "Defectiveness", "ranikamlabati");


       /* int sum = 0;
        for (Integer num : numbers) {
            sum = sum + num;
        }*/
        // System.out.println(sum);

        //int sum1   = numbers.stream().mapToInt(Integer::intValue).sum();
        //int sum1   = numbers.stream().mapToInt(i->i).sum();

        //System.out.println(sum1);

        // Integer sum2= numbers.stream().reduce(0,(a,b)->a+b);
        //Optional<Integer>sum3= numbers.stream().reduce(Integer::sum);
        Integer num4 = numbers.stream().reduce(1, (a, b) -> a * b);
        //  System.out.println(num4);
        //Integer maxvalue= numbers.stream().reduce(0,(a,b)->a > b ? a : b);
        //  Optional<Integer> maxvalue = numbers.stream().reduce(Integer::max);

        // System.out.println(maxvalue.get());
        String maxLengthStr = strList.stream().reduce((word1, word2) -> word1.length() > word2.length() ? word1 : word2).get();
        //  System.out.println(maxLengthStr);

        Double salary = EmployeeDatabase.getEmployees().stream().filter(emp -> emp.getGrade().equalsIgnoreCase("A"))
                .map(Employee::getSalary)
                .mapToDouble(i -> i).average().getAsDouble();
        //System.out.println(salary);

        Double salarySum = EmployeeDatabase.getEmployees().stream().filter(emp -> emp.getGrade().equalsIgnoreCase("A"))
                .map(Employee::getSalary)
                .mapToDouble(i -> i).sum();
        System.out.println(salarySum);
    }
}
