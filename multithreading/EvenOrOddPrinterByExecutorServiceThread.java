package com.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;


//Approach 2 Using CompletableFuture Class



public class EvenOrOddPrinterByExecutorServiceThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.rangeClosed(1, 10).forEach(num -> {

            CompletableFuture<Integer> oddCompletableFuture = CompletableFuture.
                    completedFuture(num).thenApplyAsync(x -> {
                        if (x % 2 != 0) {
                            System.out.println("Thread name: " + Thread.currentThread().getName()
                                    + " : " + x);
                        }
                        return num;
                    }, executor);
            oddCompletableFuture.join();

            CompletableFuture<Integer> evenCompletableFuture = CompletableFuture.completedFuture(num).thenApplyAsync(x -> {
                if (x % 2 == 0) {
                    System.out.println("Thread name: " + Thread.currentThread().getName()
                            + " : " + x);
                }
                return num;
            }, executor);
            evenCompletableFuture.join();
        });
    }
}
