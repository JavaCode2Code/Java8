package org.completablefuture;

import org.completablefuture.database.EmployeeDatabase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class EmployeeReminderServiceThenApplyAsync {

    public CompletableFuture<Void> sendReminderToEMployees() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployees " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();

        }).thenApplyAsync((employees) -> {
            System.out.println("filter new Joiner Employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getNewJoiner())).collect(Collectors.toList());

        }).thenApplyAsync((employees) -> {
            System.out.println("filter training not complete employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getLearningPending())).collect(Collectors.toList());



        }).thenApplyAsync((employees) -> {
            System.out.println("get Email " + Thread.currentThread().getName());
            return employees.stream().map(emp -> emp.getEmail()).collect(Collectors.toList());

        }).thenAcceptAsync((emails) -> {
            System.out.println("send Email Notification " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderServiceThenApplyAsync::sendEmail);

        });
        return completableFuture;
    }


    public static void sendEmail(String email) {
        System.out.println("sending training reminder email to : " + email);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderServiceThenApplyAsync employeeService=new EmployeeReminderServiceThenApplyAsync();
        employeeService.sendReminderToEMployees().get();
    }
}

