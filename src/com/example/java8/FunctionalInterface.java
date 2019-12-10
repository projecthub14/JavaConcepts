package com.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class FunctionalInterface {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);

        System.out.println("Print all values of list");
        printValues(list, n -> true , System.out::println);

        System.out.println("Print all even values of list");
        printValues(list, n -> n%2 == 0 , System.out::println);

        System.out.println("Print all numbers greater than 3");
        printValues(list, n -> n > 3 , System.out::println);

    }

    private static void printValues(List<Integer> list , Predicate<Integer>predicate, Consumer<Integer>consumer){
        for (int i :list) {
            if(predicate.test(i)){
                consumer.accept(i);
            }
        }
    }
}
