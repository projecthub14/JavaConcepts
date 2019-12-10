package com.example.java8.streams;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamsExample {

    public static void main(String[] args) {

        String[] arr = {"abc","efg","bcd","","efgh"};

        System.out.println(Arrays.stream(arr).filter(string -> !string.isEmpty()).collect(Collectors.toList()));

        List<String> list = Arrays.asList(arr);
        List<String> result = list.stream().filter(string  -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(result);


        //ForEach iterate each element in stream
        //limit to reduce the size of stream
        //prints 10 random numbers
        Random random = new Random();
        random.ints().limit(3).forEach(System.out::println);
        Arrays.stream(arr).forEach(System.out::println);

        //map
        List<Integer> numbers = Arrays.asList(3,2,2,3,7,3,5);

        //get list of unique squares
        List<Integer> squareList = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.println(squareList);

        //sorted method to sort the elements in stream
        Random random1 = new Random();
        random1.ints().limit(6).sorted().forEach(System.out::println);


        String[] arr1 = {"abc","efg","bcd","","efgh","","",""};
        List<String> listStr = Arrays.asList(arr1);

        //parallet stream -> for parallel processing
        long count = listStr.parallelStream().filter(str -> str.isEmpty()).count();
        System.out.println(count);

        //Collectors: combine the results of processing on the elements of stream
        List<String> filtered = listStr.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("Filtered List: " + filtered);

        String mergedString = listStr.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("Merged String: " + mergedString);

        //Statistics collectors are introduced to calculate all
        //statistics when stream processing is being done

        List numberList = Arrays.asList(3,2,2,3,7,3,5);
        IntSummaryStatistics stats = numberList.stream().mapToInt(x -> (int)x).summaryStatistics();

        System.out.println("Highest " + stats.getMax());
        System.out.println("Lowest " + stats.getMin());
        System.out.println("Sum " + stats.getSum());
        System.out.println("Average " + stats.getAverage());
    }
}
