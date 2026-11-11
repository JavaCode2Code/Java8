package com.bifuctional.interface_stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// T t,U u,R take two argu and R->Return
public class BiFunctionalDemo implements BiFunction<List<Integer>, List<Integer>, List<Integer>> {
    @Override
    public List<Integer> apply(List<Integer> list1, List<Integer> List2) {
        return Stream.of(list1, List2).flatMap(List::stream)
                .distinct().
                collect(Collectors.toList());

    }

    public static void main(String[] args) {
        // BiFunction biFunction=new BiFunctionalDemo();
        List<Integer> list1 = Stream.of(12, 4, 6, 7, 8, 9).collect(Collectors.toList());
        List<Integer> list2 = Stream.of(12, 23, 45, 15, 16, 17).collect(Collectors.toList());
        // System.out.println("Traditional Approach "+biFunction.apply(list1,list2));

        BiFunction<List<Integer>, List<Integer>, List<Integer>> biFunction1 = new BiFunction<List<Integer>, List<Integer>, List<Integer>>() {
            @Override
            public List<Integer> apply(List<Integer> l1, List<Integer> l2) {
                return Stream.of(l1, l2).flatMap(List::stream).distinct().collect(Collectors.toList());
            }
        };
        Function<List<Integer>,List<Integer>> sortedFunction=list->list.stream().sorted().collect(Collectors.toList());
        System.out.println("annonymous Approach " + biFunction1.andThen(sortedFunction).apply(list1, list2));


        BiFunction<List<Integer>, List<Integer>, List<Integer>> biFunction2 =
                (List<Integer> l1, List<Integer> l2) -> {
                    return Stream.of(l1, l2).flatMap(List::stream).distinct().collect(Collectors.toList());
                };
       // System.out.println("Lambda Approach " + biFunction2.apply(list1, list2));

        //use case increase 4000 each salary using annonymous
        Map<String, Integer> map = new HashMap<>();
        map.put("Santosh", 5000);
        map.put("Rakesh", 9000);
        map.put("Ronish", 17000);

        BiFunction<String,Integer,Integer> biFunction= new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String key, Integer value) {
                return value+4000;
            }
        };
      // map.replaceAll(biFunction);
      // System.out.println(map);
       //Lambda
        BiFunction<String,Integer,Integer> biFunction3=
          ( key,value)-> {
                return value+4000;
            };
         map.replaceAll(biFunction3);
        System.out.println(map);
    }
}
