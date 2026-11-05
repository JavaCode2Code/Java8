package com.scjp.functional;

import java.util.Arrays;
import java.util.List;

public class SupplierDemo {
    public static void main(String[] args) {
List<String> list= Arrays.asList();
System.out.println(list.stream().findAny().orElseGet(()->"No data is present"));
    }
}
