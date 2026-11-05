package com.scjp.functional;

import org.scjp.optinal.Customer;
import org.scjp.optinal.EkartDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer>predicate=num->num%2==0;
        System.out.println(predicate.test(10));
       List<Integer> listInteger= Arrays.asList(12,4,7,8,9,3,4);
       listInteger.stream().filter(num->num%2==0).forEach(System.out::println);
    }
}
