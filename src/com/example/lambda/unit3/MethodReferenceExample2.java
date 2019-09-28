package com.example.lambda.unit3;

import com.example.lambda.unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample2 {


        public static void main(String[] args) {

            List<Person> people = Arrays.asList(
                    new Person("Charles", "Dickens", 60),
                    new Person("Lewis", "Carroll", 42),
                    new Person("Thomas", "Carlyle", 51),
                    new Person("Matthew", "Arnold", 39)
            );


            System.out.println("Printing all Person");
            //system is static method
            //out is instance method
            //instance :: method name - > System.out :: println
            //shorthand for taking input argument and passing it in
            //it knows it takes input argument since its taken by consumer interface
            performConditionally(people, p -> true , System.out::println); // maps to p -> method(p)

        }


    //Predicate is generic type- its outof box interface that comes with java.util.function package
    private static void performConditionally(List< Person > people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people){
            if(predicate.test(p))
                consumer.accept(p);
        }
    }
}
