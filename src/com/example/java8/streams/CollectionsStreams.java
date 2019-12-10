package com.example.java8.streams;

import com.example.java8.concurrency.SynchronizedExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class CollectionsStreams {

    public static void main(String[] args) {

        //1. Conceptual Difference
        //1. collections are used to store the values / group of data
        //in a particular data structure like list , set
        //Streams are used to perform complex data processing operations
        //filtering , matching on stored data
        //Collections is about data ans streams is about operations on data

        List<String> list = new ArrayList<>();
        list.add("Charlie");
        list.add("Charlie");

        list.stream().distinct().forEach(System.out::println);

        //2. Data Modification
        //We can add or remove elements from collection but we cant add or remove
        //elements from streams . Streams consumes the source , perform operations
        //on it and return result. they dont modify the resource

        List<String> names = new ArrayList<>();
        names.add("Sundaraman");
        names.add("Yuki");
        names.remove(1);
         //there is no remove or add elements in stream
        Stream<String> uniqueNames = names.stream().distinct();

        //3. External vs Internal Iteration
        // Streams : no need to worry about iteration when using streams
        //it perform iteration internally behind the scene for you . You just have
        //mention the operations to be performed on source

        //we need to do iteration externally over collection using loops
        List<String> names1 = Arrays.asList("Charlie", "Douglas", "Jacob");
        for(String name : names){
            System.out.println(name);
        }

        names1.stream().map(String::toUpperCase).forEach(System.out::println);

        //4. Traversal
        //Streams are traversable only once , in order to traverse again we need to
        //get new stream from source again
        //Collections can be traverse multiple times
        List<Integer> numbers = Arrays.asList(4, 2, 8, 9, 5, 6, 7);


        Stream<Integer> stream = numbers.stream().filter(i -> i > 5);
        stream.forEach(System.out::println);

        //second time traversal throws errror .. stream is already
        //operated and closed
       // stream.forEach(System.out::println);

        //5. Eager construction vs Lazy construction
        //Collections are eagerly constructed ie all elements are computed at beginning
        //Streams are lazily constructed ie intermediate operations are not evaluated
        //until terminal operation is invoked
        System.out.println();
        List<Integer> number2 = Arrays.asList(4, 2, 8, 9, 5, 6, 7);
        number2.stream().filter(i -> i >= 5).limit(3).forEach(System.out::println);


    }
}
