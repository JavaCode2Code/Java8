package com.multithreading;

//Approach 3 Using Java8  CompletableFuture && Stream

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenOddCompletableFuture {
    static Object object = new Object();

    private static IntPredicate oddCondition=e->e % 2!=0;
    private static IntPredicate evenCondition=e->e % 2==0;

    public static void main(String[] args) throws InterruptedException {
    CompletableFuture.runAsync(()->EvenOddCompletableFuture.printResult(evenCondition));
    CompletableFuture.runAsync(()->EvenOddCompletableFuture.printResult(oddCondition));
    Thread.sleep(1000);
    }

    public static void printResult(IntPredicate condition) {
        IntStream.rangeClosed(1, 10).filter(condition).forEach(EvenOddCompletableFuture::execute);
    }

    public static void execute(int i) {
        synchronized (object){
        try {
            System.out.println("Thread name: " + Thread.currentThread().getName() + " : " + i);
            object.notify();
            object.wait();
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }}
    }
}
