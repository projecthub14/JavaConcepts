package com.example.unit2;

import com.example.unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class StandardFunctionalInterfacesExample {

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
        performConditionally(people, p -> true , p -> System.out.println(p));
        System.out.println("------------------");

        System.out.println("Printing all Person with lastName starting with C");
        performConditionally(people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));

        System.out.println("------------------");

        System.out.println("Printing all Person with firstName starting with C");
        performConditionally(people, p -> p.getFirstName().startsWith("C"), p -> System.out.println(p.getFirstName()));

    }

    //Predicate is generic type- its outof box interface that comes with java.util.function package
    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people){
           if(predicate.test(p))
             consumer.accept(p);
        }
    }



}