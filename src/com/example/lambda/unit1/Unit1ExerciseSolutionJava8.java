package com.example.lambda.unit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Unit1ExerciseSolutionJava8 {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Matthew", "Arnold", 39)
        );

        //1. Sort list by lastName

        //2. create method that prints all elements in list

        //3. create method that prints all people have lastName
        // beginning with C

        Collections.sort(people, (o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));

        System.out.println("Printing all Person");
        printConditionally(people, p-> true);
        System.out.println("------------------");

        System.out.println("Printing all Person with lastName starting with C");
        printConditionally(people, p -> p.getLastName().startsWith("C"));

        System.out.println("------------------");

        System.out.println("Printing all Person with firstName starting with C");
        printConditionally(people, p -> p.getFirstName().startsWith("C"));

    }


    private static void printConditionally(List<Person> people, Condition condition) {
        for (Person p : people){
           if(condition.test(p))
            System.out.println(p);
        }
    }



}