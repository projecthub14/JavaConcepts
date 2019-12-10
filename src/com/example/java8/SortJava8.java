package com.example.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortJava8 {


    public static void java7test(List<String> names){

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
    }

    public static void java8test(List<String> names){

        Collections.sort(names, (s1 , s2) -> s1.compareTo(s2));
    }



    public static void main(String[] args) {
        List<String> names1 = new ArrayList<>();
        names1.add("Sowmya");
        names1.add("Mahi");
        names1.add("Shaarvi");
        names1.add("Anusuya");

        System.out.println("Sort using Java 7 syntax: ");
        java7test(names1);

        names1.forEach(System.out::println);


        List<String> names2 = new ArrayList<>();
        names2.add("Jaya");
        names2.add("Viji");
        names2.add("Raji");
        names2.add("Darshan");

        System.out.println("Sort using Java 8 syntax: ");
        java8test(names2);
        //method reference points to methods by their names
        //uses :: symbol
        //method references points to static methods , instance methods ,
        // Construtor new operator (TreeSet::new)
        names2.forEach(System.out::println);



    }
}
