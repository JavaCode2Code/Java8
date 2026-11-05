package com.scjp.functional;

import java.util.ArrayList;
import java.util.List;

public class ForEachMethod {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Murrit");
        list.add("john");
        list.add("piter");
        list.add("marek");
        list.add("mac");
       /* for(String str:list){
            System.out.println(str);
        }*/
       // list.stream().forEach(str->System.out.println(str));
        list.stream().forEach(System.out::println);
    }

}
