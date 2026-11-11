package com.optional;

import com.mapAndFlatMap.Customer;
import lombok.var;

import java.util.Arrays;
import java.util.Optional;

public class OptinalTest {
    public static void main(String[] args) throws Exception {


        var cust = new Customer(101, "john", null, Arrays.asList("397937955", "21654725"));
        Optional<Object> optional = Optional.empty();
        // System.out.println(optional);



        //  Optional<String> optionalStr=Optional.of(cust.getEmail());
        Optional<String> optionalStr2 = Optional.ofNullable(cust.getEmail());


        //System.out.println(optionalStr2);//return Empty if not present


        // String s = optionalStr2.isPresent() ? optionalStr2.get() : optionalStr2.get();
        //  out.println();


        //out.println(optionalStr2.orElse("Default@gmail.com"));


        //  out.println(optionalStr2.orElse("Client is not provided this value"));


        //  out.println(optionalStr2.map(String::toUpperCase).orElseGet(()-> " No Value added "));


        // out.println(optionalStr2.orElseThrow(()-> new IllegalArgumentException("No value provided for This")));


        getEmail("prq");


    }

    public static com.optional.Customer getEmail(String email) throws Exception {
        //  return EkartDataBase.getAll().stream().filter(cust->cust.getEmail().equalsIgnoreCase(email)).findAny().get();


        // return EkartDataBase.getAll().stream().filter(cust->cust.getEmail().equalsIgnoreCase(email)).findAny().orElse(new com.optional.Customer());


        return EkartDataBase.getAll().stream().filter(cust -> cust.getEmail().equalsIgnoreCase(email)).findAny().orElseThrow(() -> new Exception("Value is not present"));

    }
}
