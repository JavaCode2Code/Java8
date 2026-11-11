package com.scjp.functional;

import java.util.Arrays;
import java.util.List;

public class ConsumerDemo {
    public static void main(String[] args) {
        /*Consumer consumer=t-> System.out.println(t);
        consumer.accept("Hi Sateesh");
*/
        List<Integer> listInteger = Arrays.asList(12, 4, 7, 8, 9, 3, 4);
        listInteger.forEach(num -> System.out.println(num));
    }

}
