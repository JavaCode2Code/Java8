package com.bifuctional.interface_stream;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerdemo implements BiConsumer<String, Integer> {


    @Override
    public void accept(String i1, Integer i2) {
        System.out.println("Input1 " + i1 + " Input2 " + i2);
    }

    public static void main(String[] args) {
        BiConsumer<String, Integer> biConsumer = new BiConsumerdemo();
        // biConsumer.accept("Hello", 9190);


        //annonymous approach2

        BiConsumer<String, Integer> biConsumer1 = new BiConsumer<String, Integer>() {
            @Override
            public void accept(String str, Integer integer) {
                System.out.println(str + " " + integer);
            }
        };
        biConsumer1.accept("hello", 786);


        //Lambada approach3

        BiConsumer<String, Integer> biConsumer2 = (str, integer) -> {
            System.out.println(str + " " + integer);
        };

        biConsumer2.accept("Sateesh", 786);
//use case
        Map<String, Integer> map = new HashMap<>();
        map.put("Santosh", 5000);
        map.put("Rakesh", 9000);
        map.put("Ronish", 17000);
        map.forEach((k,v)->System.out.println(k +","+v));

    }
}
