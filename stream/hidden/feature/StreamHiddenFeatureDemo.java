package com.stream.hidden.feature;

import com.method.reference.Employee;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamHiddenFeatureDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Rakesh", null, "Brajesh", null, "Ranju", null, "Manju", "Minu");
        List<String> notNull = names.stream().filter(name -> name != null).collect(Collectors.toList());
        //  List<String> notNull = names.stream().filter(Objects::nonNull).collect(Collectors.toList());

        System.out.println(notNull);

        List<String> collect = names.stream().flatMap(Stream::ofNullable).collect(Collectors.toList());
        System.out.println(collect);

        Stream<Integer> limit = Stream.iterate(0, n -> n + 1).limit(20);
        //System.out.println(Arrays.toString(limit.toArray()));

        List<Employee> employees = Arrays.asList(
                new Employee("Satish", 9000),
                new Employee("Milan", 12000),
                new Employee("Manoj", 32000),
                new Employee("Rajesh", 23000)
        );

        //collectingThenAnd
        long collect1 = employees.stream().mapToDouble(Employee::getSalary).boxed().collect(Collectors.collectingAndThen(
                Collectors.averagingDouble(Double::doubleValue)//1 st collector
                , Math::round));

        System.out.println(collect1);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> tak = list.stream().takeWhile(n -> (n < 5)).collect(Collectors.toList());
        System.out.println(tak);

//drop while
        List<Integer> drop = list.stream().dropWhile(n -> (n < 5)).collect(Collectors.toList());
        System.out.println(drop);


        List<Integer> result = list.stream().dropWhile(n -> n < 3)
                .takeWhile(n -> (n < 7)).collect(Collectors.toList());
        System.out.println(result);


        //Collectors.teeing
        Map<String, Integer> collect2 = list.stream().collect(Collectors.teeing(Collectors.maxBy(Integer::compareTo), Collectors.minBy(Integer::compareTo),
                (c1, c2) -> Map.of("max", c1.get(), "min", c2.get())));
        System.out.println(collect2);


        //concat two stream
        Stream<Integer> stream1 = Stream.of(12, 13, 4, 5);
        Stream<Integer> stream2 = Stream.of(10, 3, 8, 9);
        Stream<Integer> concat = Stream.concat(stream1, stream2);
        int sum = concat.mapToInt(Integer::intValue).sum();


        System.out.println(sum);
        //partitioningBy
        Map<Boolean, List<Integer>> collect3 = list.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("odd numer " + collect3.get(Boolean.FALSE));
        System.out.println("Even numer " + collect3.get(Boolean.TRUE));

        //IntStream rangeClosed method
        List<Integer> rang = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
        System.out.println(rang);


        //Collections.nCopies

        List<String> list2 = Collections.nCopies(5, "Macy");
        System.out.println(list2);



        try {
            list2.set(0, "Manju");
        } catch (UnsupportedOperationException ex) {
            System.out.println("Not supported yet.");
            ex.getMessage();

        }




        //Collection.frequency
        List<Integer> numbers = Arrays.asList(1, 2, 3, 6, 6, 4, 8, 5, 5, 4, 3, 6, 7, 8, 9);
        int num = Collections.frequency(numbers, 5);
        System.out.println("4 Present number of time  " + num);
        //find occurrence of each the element in given list
        Map<Integer, Integer> result2 = numbers.stream().collect(Collectors.toMap(number -> number, number -> Collections.frequency(numbers, number), (existingValue, newvalue) -> existingValue));
        System.out.println(result2);


        //Collections.disjoine
        List<Integer> numlist1=Arrays.asList(1,2,3,4,8,9);
        List<Integer> numList2=Arrays.asList(11,12,13,14);
        boolean disres = Collections.disjoint(numlist1, numList2);
        if(disres){
            System.out.println("both list are not common ");
        }else {
            System.out.println("both list are common ");

        }
            //Collections.singleton
            Set<String> codeTocode = Collections.singleton("codeTocode");
            try{
                codeTocode.add("java Code");
                System.out.println(codeTocode);
            }
            catch(UnsupportedOperationException ex){
                System.out.println("Set Immutable Not able modify.");
            }

     //    Collections.rotate();
            List<Integer> list5=new ArrayList<>();
            for(int i=1;i<=10;i++){
                list5.add(i);
            }
      /*      System.out.println(list5);
     Collections.rotate(list5, 3);
     System.out.println(list5);*/

       /* Collections.rotate(list5, -4);
        System.out.println(list5);*/

       /* Collections.rotate(list5, list5.size());
        System.out.println(list5);*/

        Collections.rotate(list5, -15);
        System.out.println(list5);
    }


}
