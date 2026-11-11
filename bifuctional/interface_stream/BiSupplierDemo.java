package com.bifuctional.interface_stream;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class BiSupplierDemo {

    // Supplier backed by java.util.Random (works on Java 8+)
    static class RandomNumberSupplier implements Supplier<Integer> {
        private final Random random;


        RandomNumberSupplier(Random random) {
            this.random = random;
        }


        @Override
        public Integer get() {
            return random.nextInt(100);
        }
    }

    public static void main(String[] args) {
        Supplier<Integer> s1 = new RandomNumberSupplier(new Random());
        System.out.println(s1.get());



        // Or as a lambda with ThreadLocalRandom
        Supplier<Integer> s2 = () -> ThreadLocalRandom.current().nextInt(100);
        System.out.println(s2.get());


    }
}