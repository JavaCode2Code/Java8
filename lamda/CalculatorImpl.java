package com.lamda;

public class CalculatorImpl {
    public static void main(String[] args) {
      /*  Calculator calculator = (a, b)->{
            System.out.println(a+b);
        };

        calculator.add(5, 10);*/

       /* Calculator calculator=(input)->{System.out.println(input*input*input);};
           calculator.cube(5);*/
        Calculator calculator=(a,b)->{
            return (a-b);
        };
        int result = calculator.subtract(8,2);
        System.out.println(result);
    }
}

