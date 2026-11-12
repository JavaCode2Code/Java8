package org.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class WhyNotFuture {


    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService service = Executors.newFixedThreadPool(10);

        //IT can not manually Completed

        Future<List<? extends Number>> future = service.submit(() -> {
            System.out.println("Thread" +Thread.currentThread().getName());
            delay(1);

            return Arrays.asList(1, 31, 1, 15, 17, 16, 18, 19.10, 12);
        });

      //  List<Integer> list = (List<Integer>) future.get();
      //  System.out.println(list);

       Future<List<? extends Number>> future1 = service.submit(() -> {
            System.out.println("Thread" +Thread.currentThread().getName());
             System.out.println(10/0);
            return Arrays.asList(1, 31, 1, 15, 17, 16, 18, 19.10, 12);
        });
       // List<Integer> list = (List<Integer>) future1.get();
       // System.out.println(list);
/*
        Future<List<? extends Number>> futur2 = service.submit(() -> {
            System.out.println("Thread" +Thread.currentThread().getName());
            delay(1);
            return Arrays.asList(1, 31, 1, 15, 17, 16, 18, 19.10, 12);
        });

        future.get();
        future1.get();
        futur2.get();*/

    }

    public static void delay(int minite) throws ExecutionException, InterruptedException {
        try {
            TimeUnit.MINUTES.sleep(minite);
        } catch (InterruptedException ex) {
            ex.fillInStackTrace();
        }

        //CompletableFuture
        CompletableFuture<String> completableFuture=new CompletableFuture<>();
        completableFuture.get();
    }
}
