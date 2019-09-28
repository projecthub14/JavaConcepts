package com.example.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {

    public static void main(String[] args) throws IOException {

        //1.
        //Intstream is stream of primitive  values
        //this replaces tradition for loops
        //prints from 1-9 in same line
        IntStream
                .range(1,10) //gives range of integer from 1 - 9
                .forEach(System.out::print); //terminal operation

        System.out.println();

        //2. Integer stream with skip
        IntStream
                .range(1,10)
                .skip(5) // skips first 5 elements
                .forEach(System.out::print);

        System.out.println();

        //3. IntStream stream with num
        int sum = IntStream
                .range(1,5)
                .sum();

        System.out.println("Sum is " + sum);

        //4. Stream.of , sorted and findFirst , ifPresent
        Stream.of("Ava" , "Ameri","Alberto") // creating stream using of function
                .sorted() //straight alphabetical sort .. we can pass comparator
                .findFirst()
                .ifPresent(System.out::println);

        //5. Stream from Array , sort , filter and print
        String[] names = {"Al","Ankit","Kushal","Breant","Sarika","amanda","Hans","Shivika"};
        Arrays.stream(names) // same as Stream.of(names)
        .sorted()
        .filter(p -> p.startsWith("S"))
        .forEach(System.out::println);


        //6. Average of squares of int[]

        Arrays.stream(new int[]{2,3,4,6,8,9})
                .map(x -> x * x)
                .average() // this returns OptionalDouble
                .ifPresent(System.out::println); //so check isPresent

        //7. Stream from List , filter and print

        List<String> people = Arrays.asList("Al","Ankit","Kushal","Breant","Sarika","amanda","Hans","Shivika");
        people.stream()
                .map(String::toLowerCase) //a -> a.toLowerCase()
                .filter(a -> a.startsWith("a"))
                .forEach(System.out::println);

        //8. Stream rows from text file , sort , filter , and print
//        Stream<String> band = Files.lines(Paths.get("bands.txt"));
//        band.sorted()
//            .filter( x -> x.length() > 13)
//            .forEach(System.out::println);
//
//        band.close();







    }
}
