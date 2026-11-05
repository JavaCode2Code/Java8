package com.scjp.functional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer,String> map=new HashMap<>();
        map.put(1,"Sateesh");
        map.put(2,"Ramesh");
        map.put(3,"Raju");
        map.forEach((k,v)->System.out.println(k+" "+v));
    }

}
