package com;

import java.util.ArrayList;
import java.util.List;

public class LoopVSStreamRace {
    private static List<Integer> numers = new ArrayList<>();

    static {
        for (int i = 1; i < 10000; i++) {
            numers.add(i);
        }

    }

    public static long forLoopSum() {
        long sum = 0;
        for (int i : numers) {
            sum = sum + i;
        }
        return sum;
    }

    public static long streamForLoopSum() {
        return numers.stream().mapToLong(l -> l).sum();

    }

    public static long parallelForLoopSum() {
        return numers.parallelStream().mapToLong(Integer::longValue).sum();
    }

   /*
    Result1
    For Loop| Result: 1249999975000000| Time 93 ms
    For Strem| Result: 1249999975000000| Time 72 ms
    For ParallelStream | Result: 1249999975000000| Time 57 ms

    Result2
       For Loop| Result: 1249999975000000| Time 61 ms
       For Strem| Result: 1249999975000000| Time 82 ms
       For ParallelStream | Result: 1249999975000000| Time 25 ms

    Result3
       For Loop| Result: 1249999975000000| Time 71 ms
       For Strem| Result: 1249999975000000| Time 78 ms
       For ParallelStream | Result: 1249999975000000| Time 42 ms

       50 millions
       10 millions 1 chunk
       4 cores   [1,2,3,4]
    */


    public static void main(String[] args) {
        System.out.println("JVM Warm Up Started....");
        for (int i = 0; i <= 5; i++) {
            forLoopSum();
            streamForLoopSum();
            parallelForLoopSum();

        }

        System.out.println("JVM Warm Up End....");


        long start = System.currentTimeMillis();
        long forLoopSum = forLoopSum();
        long end = System.currentTimeMillis();
        System.out.println("For Loop| Result: " + forLoopSum + "| Time " + (end - start) + " ms");
        long startStrem = System.currentTimeMillis();

        long streamLoopSum = streamForLoopSum();

        long endStrema = System.currentTimeMillis();
        System.out.println("For Strem| Result: " + streamLoopSum + "| Time " + (endStrema - startStrem) + " ms");

        long startParalle = System.currentTimeMillis();

        long paralleLoopSum = parallelForLoopSum();
        long endParalle = System.currentTimeMillis();

        System.out.println("For ParallelStream | Result: " + paralleLoopSum + "| Time " + (endParalle - startParalle) + " ms");

    }


}
