package com.qa;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8QA {
    public static void main(String[] args) {

        //find Each character occur
        String str = "adheteSknhsns";
        Map<String, Long> map = Arrays.stream(str.toLowerCase().split("")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        //  System.out.println(map);


        //Find the duplicate char
        List<String> map2 = Arrays.stream(str.toLowerCase().split("")).
                collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet().stream().filter(x -> x.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        //  System.out.println(map2);


        //unique value non-repeating character

        List<String> map3 = Arrays.stream(str.toLowerCase().split("")).
                collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
        //  System.out.println(map3);


        //first non-repeating char

        List<String> map4 = Collections.singletonList(Arrays.stream(str.toLowerCase().split("")).
                collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(x -> x.getValue() == 1).findFirst().get().getKey());
        //   System.out.println("First Non repeating char" + map4);


        //first repeating char

        List<String> map5 = Collections.singletonList(Arrays.stream(str.toLowerCase().split("")).
                collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new, Collectors.counting())).entrySet().stream().filter(x -> x.getValue() > 1).findFirst().get().getKey());
        //System.out.println("First repeating char" + map5);


        //Second-Highest Number in array
        int[] intArray = {1, 12, 161, 181, 19, 23, 28};
        Integer secondHigh = Arrays.stream(intArray).boxed().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(secondHigh);


        Map<String, Integer> map6 = new HashMap<>();
        map6.put("Rakesh", 15000);
        map6.put("Brajesh", 15000);
        map6.put("Manoj", 16000);
        map6.put("Rani", 15000);
        map6.put("Dinesh", 20000);
        map6.put("Arun", 20000);
        map6.put("Karan", 16000);
        map6.put("Dinu", 170000);
        map6.put("Prasanta", 170000);

        //System.out.println(nthHighestSalary(4, map6));

        //sorted by value 16000[karan,Manoj]
        Map<Integer, List<String>> collect = map6.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        System.out.println(collect);



        //second-highest salary
        System.out.println(secondHighestSalary(3,map6));
    }

    public static Map.Entry<String, Integer> nthHighestSalary(int num, Map<String, Integer> map) {

        return map.entrySet().stream().sorted(Collections.reverseOrder(
                (Map.Entry.comparingByValue()))).collect(Collectors.toList()).get(num - 1);


    }

    public static Map.Entry<Integer, List<String>> secondHighestSalary(int num, Map<String, Integer> map){
        return map.entrySet().stream().
                collect(Collectors.groupingBy
                        (Map.Entry::getValue, Collectors.mapping
                                (Map.Entry::getKey,
                                        Collectors.toList()))).entrySet().stream().sorted
                        (Collections.reverseOrder(Map.Entry.
                                comparingByKey())).collect(Collectors.toList()).get(num);

    }
}
