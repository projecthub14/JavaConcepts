package com.example.lambda.unit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Unit1ExerciseSolutionJava7 {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles","Dickens",60),
                new Person("Lewis","Carroll",42),
                new Person("Thomas","Carlyle",51),
                new Person("Matthew","Arnold",39)
        );

        //1. Sort list by lastName

        //2. create method that prints all elements in list

        //3. create method that prints all people have lastName
        // beginning with C

        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        System.out.println("Printing all Person");
        printAll(people);
        System.out.println("------------------");

        System.out.println("Printing all Person with lastName starting with C");
        printConditionally(people, new Condition() {

            @Override
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        System.out.println("------------------");

        System.out.println("Printing all Person with firstName starting with C");
        printConditionally(people, new Condition() {

            @Override
            public boolean test(Person p) {
                return p.getFirstName().startsWith("C");
            }
        });
    }


    private static void printAll(List<Person> people) {

        for(Person p : people) {
            System.out.println(p);
        }
    }



    private static void printConditionally(List<Person> people, Condition condition) {

        for(Person p : people) {
            if(condition.test(p))
              System.out.println(p);
        }
    }





}
// Its overhead to include condition to do generic operation
//Replaced by Predicate -> its genericType which returns boolean and takes generic type as parameter
interface Condition {
    boolean test(Person p);
}