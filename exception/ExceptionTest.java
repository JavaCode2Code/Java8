package com.exception;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ExceptionTest {

    public static void printList(String str) {
        try {
            System.out.println(Integer.parseInt(str));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static Consumer<String> handleUncheckedExceptionIfAny(Consumer<String> payload){
        return obj-> {
            try {
                payload.accept(obj);
            } catch (Exception ex) {
                System.out.println("Exception " + ex.getMessage());
            }

        };
    }
    static <Target,ObjEx extends Exception> Consumer<Target> handleUncheckedGenericExceptionIfAny(Consumer<Target> targetConsumer,Class<ObjEx> objExClass){
        return obj-> {
            try {
                targetConsumer.accept(obj);
            } catch (Exception ex) {
                try{
                    ObjEx objEx=objExClass.cast(ex);
                    System.out.println("Exception " + objEx.getMessage());

                }
                catch (ClassCastException exe){
                    throw exe;
                }
            }

        };
    }

    static <Target,ObjEx extends Exception> Consumer<Target> handleCheckedGenericExceptionConsumer(CheckedExceptionHandlerConsumer<Target,Exception> handlerConsumer){
        return obj->{
            try{
                handlerConsumer.accept(obj);
            }
            catch (Exception ex){
                throw new RuntimeException(ex);
            }
        };
    }
    public static void main(String[] args) {
        //Unchecked Exception
        List<String> listStr = Arrays.asList("123", "345", "xpv");
       List<Integer> integerList= Arrays.asList(1,5,8,1,0);
      // integerList.forEach(handleUncheckedGenericExceptionIfAny(s->System.out.println(10/s),ArithmeticException.class));
        // listStr.stream().map(Integer::parseInt).collect(Collectors.toList());
        // listStr.forEach(s->System.out.println(Integer.parseInt(s)));
        //listStr.stream().forEach(handleUncheckedGenericExceptionIfAny(s->System.out.println(Integer.parseInt(s)),NumberFormatException.class));
       //Checked Exception
        List<Integer> listInt=Arrays.asList(12,14,15,16,0);
listInt.forEach(handleCheckedGenericExceptionConsumer(i->{
    Thread.sleep(i);
    System.out.println(i);
}));
    }



}
