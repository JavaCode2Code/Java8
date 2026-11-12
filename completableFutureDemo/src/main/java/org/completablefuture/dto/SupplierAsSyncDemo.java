package org.completablefuture.dto;

import org.completablefuture.database.EmployeeDatabase;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplierAsSyncDemo {
    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {
        CompletableFuture<List<Employee>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executed By :" + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        });
        return future.get();
    }



    public List<Employee> getEmployeesWithExecutor() throws ExecutionException, InterruptedException {
        Executor executor= Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Executed with Executor By  :" + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        },executor);
        return future.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplierAsSyncDemo syncDemo = new SupplierAsSyncDemo();
        List<Employee> employees = syncDemo.getEmployees();


        // employees.forEach(System.out::println);
      //  syncDemo.getEmployeesWithExecutor().forEach((System.out::println));
        List<Employee> employees2 = syncDemo.getEmployeesWithExecutor();
        System.out.println(employees.size());
        System.out.println(employees2.size());
    }
}
