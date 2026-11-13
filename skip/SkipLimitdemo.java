package com.skip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SkipLimitdemo {
    public static void main(String[] args) throws IOException {
        List<Integer> list= Arrays.asList(12,13,16,17,18,7,8,9,10);
        list.stream().skip(5).limit(7).forEach(System.out::println);
System.out.println("=====================================================");
        List<String> fileData= Files.readAllLines(Paths.get("C:\\Users\\Sateesh\\Documents\\Spring\\Spring Boot app\\pipeline\\CodingPracticeExample\\src\\data.txt"));
       // fileData.forEach(System.out::println);
     //   System.out.println(fileData.size());
fileData.stream().skip(1).limit(fileData.size()-2).forEach(System.out::println);
    }
}
