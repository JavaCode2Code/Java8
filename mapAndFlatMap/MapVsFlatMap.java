package com.mapAndFlatMap;

import java.util.List;
import java.util.stream.Collectors;

public class MapVsFlatMap {
    public static void main(String[] args) {




        List<Customer> listCust = EkartDataBase.getAll();
        //List<Customer> convert as List<String> OneToOne mapping




        List<String> listEmail = listCust.stream().map(cust -> cust.getEmail()).collect(Collectors.toList());
        List<String> list = listCust.stream().map(cust -> cust.getName()).collect(Collectors.toList());



        List<List<String>> list2 = listCust.stream().map(cust -> cust.getPhoneNumbers()).collect(Collectors.toList());
        //Flatmap OneToMany mapping
        List<String> phoneNumbers = listCust.stream().flatMap(cust -> cust.getPhoneNumbers().stream()).collect(Collectors.toList());

        System.out.println(phoneNumbers);


    }

}