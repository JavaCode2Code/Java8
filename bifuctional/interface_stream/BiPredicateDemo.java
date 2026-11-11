package com.bifuctional.interface_stream;

import java.util.function.BiPredicate;

public class BiPredicateDemo  {


    public static void main(String[] args) {
        BiPredicate<String,String> biPredicate=new BiPredicate<String, String>() {
            @Override
            public boolean test(String s, String s2) {
                return s.equalsIgnoreCase(s2);
            }
        };


      //  System.out.println(biPredicate.test("Madam","madam"));



        //Lambda approach

        BiPredicate<String,String> biPredicate2=( s, s2)->
                (s.equalsIgnoreCase(s2));


        BiPredicate<String,String> biPredicate3=( s, s2)->
           s.length()==s2.length();


       boolean valu= biPredicate3.and(biPredicate2).test("Papa","papa");
        //System.out.println(valu);



        boolean value= biPredicate3.or(biPredicate2).test("Papa","papa");
        System.out.println(value);

    }
}
