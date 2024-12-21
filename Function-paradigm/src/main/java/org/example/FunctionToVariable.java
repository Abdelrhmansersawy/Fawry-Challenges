package org.example;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class FunctionToVariable {
    public static void main(String[] args) {
        BinaryOperator<Integer> add = (a,b) -> a + b;
        System.out.println(add.apply(2,3));
    }

}
