package com.example.lambda.unit3;

import com.example.lambda.unit1.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

//Check Collection and Stream
public class StreamsExample1 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 60),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Matthew", "Arnold", 39)
        );

        //every collection comes with stream methos -> it returns Stream<Person
        //by anology stream is assembly line where all multiple operations are performed for each element of stream
        //each operation can modify/remove the stream

        // 3 parts to streams -> 1. source which is collection
        //2. all operations to be performed on the stream
        //3. terminal operation -> end condition -> this is what causes stream to act

        people.stream()
                .filter(p -> p.getLastName().startsWith("C"))
                .forEach(person -> System.out.println(person.getFirstName())); // this is one operation on assembly

        // nothing happens
        Stream<Person> stream = people.stream(); //this is view of collection

        // just operation is on stream but we have not started yet
        people.stream()
                .filter(p -> p.getLastName().startsWith("C"));

        //when we add terminal condition which gives result -> number of elements

        //stream is internal iteration by giving source and operation
        // so we can have a portion of huge collection in different assembly line
        //basically split collection using parallelStream()
        long count = people.stream()
                .filter(p -> p.getLastName().startsWith("D"))
                .count(); // after this we cannot have any other methods

        System.out.println("Count is " + count);

        long count1 = people.parallelStream()
                .filter(p -> p.getLastName().startsWith("D"))
                .count(); // after this we cannot have any other methods

        System.out.println("Count is " + count1);



    }
}
