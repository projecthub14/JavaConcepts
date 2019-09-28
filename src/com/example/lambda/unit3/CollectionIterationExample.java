package com.example.lambda.unit3;

import com.example.lambda.unit1.Person;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Matthew", "Arnold", 39)
        );

        //external iterator -> controlling iteration
        //since we are controlling its difficult to run in multiple threads
        System.out.println("Traditional for loop ");
        for (int i = 0; i <people.size() ; i++) {
            System.out.println(people.get(i));

        }

        // external iterator -- not mainting index -> but still controlling iteration
        System.out.println(" for in  loop ");
        for(Person p : people){
            System.out.println(p);
        }

        System.out.println(" lambda for each  loop ");
        //internal iterator -- giving control to somebody else

        // iteration happens in runtime
        //calling forEach on each collection which takes argument which is consumer
        // consumer is functional interface which comes out of box in java 8
        // we can pass lambda expression
        //this is useful in running in multiple threads

        people.forEach(person -> System.out.println(person));

        System.out.println(" lambda for each  loop Method Reference ");
        people.forEach(System.out::println);

    }


}
