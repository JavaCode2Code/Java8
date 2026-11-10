package com.parallelStrema;

import com.map_reduce.Employee;
import com.map_reduce.EmployeeDatabase;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStream {
    public static void main(String[] args) {
        long start=0;
        long end=0;
      /*  start= System.currentTimeMillis();
        IntStream.rangeClosed(1,100).forEach(System.out::println);
        end= System.currentTimeMillis();
        System.out.println("Plan Stream Look Time " +(end-start));*/
System.out.println("********************************************");
        start= System.currentTimeMillis();
        IntStream.rangeClosed(1,100).parallel().forEach(System.out::println);
        end= System.currentTimeMillis();
        System.out.println("parallel Stream Look Time " +(end-start));

    IntStream.rangeClosed(1,10).forEach(x->{
        System.out.println("Normal Thread "+ Thread.currentThread().getName()+" "+x );
    });

        IntStream.rangeClosed(1,10).parallel().forEach(x->{
            System.out.println("Parallel Thread "+ Thread.currentThread().getName()+" "+x );
        });
        List<Employee> listEMp= EmployeeDatabase.getParallelEmployees();
        start= System.currentTimeMillis();
        listEMp.stream().map(em->em.getSalary()).mapToDouble(Double::doubleValue).average();
        end= System.currentTimeMillis();
        System.out.println("Plan Strema Employee time "+(end-start));

        System.out.println("##########################################");

        start= System.currentTimeMillis();
        listEMp.parallelStream().map(em->em.getSalary()).mapToDouble(Double::doubleValue).average().getAsDouble();
        end= System.currentTimeMillis();
        System.out.println("parallel Strema Employee Time "+(end-start));
    }
}
