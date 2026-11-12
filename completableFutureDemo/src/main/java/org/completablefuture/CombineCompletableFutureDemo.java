package org.completablefuture;

import org.completablefuture.database.EmployeeDatabase;
import org.completablefuture.dto.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CombineCompletableFutureDemo {
    public CompletableFuture<Employee> getEmployeeDetails() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getEmployeeDetails() " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees().stream().
                    filter(emp -> "21-825-2623".equalsIgnoreCase(emp.getEmployeeId())).findAny().orElse(null);
        });
    }

    public CompletableFuture<Integer> getRatings(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getRatings() " + Thread.currentThread().getName());
            return employee.getRating();
        });
    }

    public CompletableFuture<String> getFirstName(Employee employee) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("firstName() " + Thread.currentThread().getName());
            return employee.getFirstName();
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //combine dependent future (2) -> thenCompose
        //employee -> ratings
        CombineCompletableFutureDemo cf = new CombineCompletableFutureDemo();
        CompletableFuture<Integer> completableResult = cf.getEmployeeDetails().thenCompose(cf::getRatings);
        System.out.println("rating:" + completableResult.get());

        CompletableFuture<String> completableResult2 = cf.getEmployeeDetails().thenCompose(cf::getFirstName);
        System.out.println("Firstname:" + completableResult2.get());


        //combine independent future (2) -> thenCombine
        //1-  group employee and counts
        CompletableFuture<Map<String, Long>> employeeMapfuture = CompletableFuture.supplyAsync(() -> {
            return EmployeeDatabase.fetchEmployees()
                    .stream().
                    collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        });


        CompletableFuture<List<String>> emailFutue = CompletableFuture.supplyAsync(() -> {
            return EmployeeDatabase.fetchEmployees().stream().map(Employee::getEmail).collect(Collectors.toList());
        });

        CompletableFuture<List<String>> mapData = employeeMapfuture.thenCombine(emailFutue, (empMap, emails) -> Collections.singletonList(empMap + " " + emails));

        System.out.println(mapData.get());


        //combine multiple independent future (more than 2) -> allOf(n task)
        //-> weatherDetailsAPI
        //-> news apis
        //-> stockPrice apis

        //combine multiple independent future (more than 2) -> anyOf(n task) (no need to wait for all)
        //Location -> X Y -> result
        //stock price -> x ,y , z ->
    }

}





