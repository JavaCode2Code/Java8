package org.completablefuture;

import org.completablefuture.dto.Employee;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class RunAsSyncDemo {

    public Void saveEmployee(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();

        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync((new Runnable() {
            @Override
            public void run() {
                List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {

                });
                //write logic t save list of employee to database
                //repository.saveAll(employees);

                System.out.println("Thread" + Thread.currentThread().getName());
              //  employees.stream().forEach(System.out::println);
                System.out.println( employees.size());
            }
        }));
        return runAsyncFuture.get();
    }
    public Void saveEmployeeWithLambda(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync((()->{
                List<Employee> employees = mapper.readValue(jsonFile, new TypeReference<List<Employee>>() {

                });
                //write logic t save list of employee to database
                //repository.saveAll(employees);

                System.out.println("Thread" + Thread.currentThread().getName());
                System.out.println( employees.size());
            })
        ,executor);
        return runAsyncFuture.get();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsSyncDemo futureDemo = new RunAsSyncDemo();
       futureDemo.saveEmployee(new File("completableFutureDemo\\employees.json"));
        futureDemo.saveEmployeeWithLambda(new File("completableFutureDemo\\employees.json"));

       // System.out.println("Hello World!");

    }
}
