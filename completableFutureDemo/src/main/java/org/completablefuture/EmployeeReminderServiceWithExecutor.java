package org.completablefuture;

import org.completablefuture.database.EmployeeDatabase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeReminderServiceWithExecutor {

    public CompletableFuture<Void> sendReminderToEMployees() {
        ExecutorService execute = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployees " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();
        }, execute).thenApplyAsync((employees) -> {
            System.out.println("filter new Joiner Employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getNewJoiner())).collect(Collectors.toList());
        }, execute).thenApplyAsync((employees) -> {
            System.out.println("filter training not complete employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getLearningPending())).collect(Collectors.toList());
        }, execute).thenApplyAsync((employees) -> {
            System.out.println("get Email " + Thread.currentThread().getName());
            return employees.stream().map(emp -> emp.getEmail()).collect(Collectors.toList());
        }, execute).thenAcceptAsync((emails) -> {
            System.out.println("send Email Notification " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderServiceWithExecutor::sendEmail);

        }, execute);
        return completableFuture;
    }



    public CompletableFuture<Void> sendReminderWithDiffExecutor() {
        ExecutorService execute = Executors.newFixedThreadPool(5);

        ExecutorService execute2 = Executors.newFixedThreadPool(5);

        ExecutorService execute3 = Executors.newFixedThreadPool(5);

        ExecutorService execute4 = Executors.newFixedThreadPool(5);

        ExecutorService execute5 = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployees " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();


        }, execute).thenApplyAsync((employees) -> {
            System.out.println("filter new Joiner Employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getNewJoiner())).collect(Collectors.toList());


        }, execute2).thenApplyAsync((employees) -> {
            System.out.println("filter training not complete employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getLearningPending())).collect(Collectors.toList());


        }, execute3).thenApplyAsync((employees) -> {
            System.out.println("get Email " + Thread.currentThread().getName());
            return employees.stream().map(emp -> emp.getEmail()).collect(Collectors.toList());



        }, execute4).thenAcceptAsync((emails) -> {
            System.out.println("send Email Notification " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderServiceWithExecutor::sendEmail);

        }, execute5);
        return completableFuture;
    }


    public static void sendEmail(String email) {
        System.out.println("sending training reminder email to : " + email);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderServiceWithExecutor employeeService=new EmployeeReminderServiceWithExecutor();
       // employeeService.sendReminderToEMployees().get();
        employeeService.sendReminderWithDiffExecutor().get();
    }
}

