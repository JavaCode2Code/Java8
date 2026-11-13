package com.method.reference;

public class Test {
    public void m1() {
        System.out.println(" instance MethodReferencedDemo.m1");
    }

    public static void m2() {
        System.out.println(" Static MethodReferencedDemo.m2");
    }

    public static void main(String[] args) {
        Test test = new Test();
      //  test.m1();

      //  test::m1(); //Instance Method reference

      //  test::m2(); //static method reference

        //constructor reference



    }
}
