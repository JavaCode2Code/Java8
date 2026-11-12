package org.completablefuture;

import org.completablefuture.database.EmployeeDatabase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeReminderServiceThenApply {

    public CompletableFuture<Void> sendReminderToEMployees() {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployees " + Thread.currentThread().getName());
            return EmployeeDatabase.fetchEmployees();

        }).thenApply((employees) -> {
            System.out.println("filter new Joiner Employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getNewJoiner())).collect(Collectors.toList());


        }).thenApply((employees) -> {
            System.out.println("filter training not complete employee: " + Thread.currentThread().getName());
            return employees.stream().filter(emp -> "TRUE".equalsIgnoreCase(emp.getLearningPending())).collect(Collectors.toList());
        }

        ).thenApply((employees) -> {
            System.out.println("get Email " + Thread.currentThread().getName());
            return employees.stream().map(emp -> emp.getEmail()).collect(Collectors.toList());


        }).thenAccept((emails) -> {
            System.out.println("send Email Notification " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderServiceThenApply::sendEmail);

        });
        return completableFuture;
    }

    public static void sendEmail(String email) {
        System.out.println("sending training reminder email to : " + email);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderServiceThenApply employeeService=new EmployeeReminderServiceThenApply();
        employeeService.sendReminderToEMployees().get();
    }
}

