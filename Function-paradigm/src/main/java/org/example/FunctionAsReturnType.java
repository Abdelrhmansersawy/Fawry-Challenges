package org.example;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionAsReturnType {
    public static Function<Integer,Integer> multiplyBy(int x){
        return (y) -> x*y;
    }
    public static void main(String []arg){
        Function<Integer,Integer> multiplyByFive = multiplyBy(5);
        System.out.println("5 x 3 = " + multiplyByFive.apply(3));
        System.out.println("5 x 2 = " + multiplyByFive.apply(2));
    }
}
