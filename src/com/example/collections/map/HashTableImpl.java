package com.example.collections.map;

import java.util.Hashtable;
import java.util.Map;

public class HashTableImpl {

    public static void main(String[] args) {

        Hashtable<Integer,String> days = new Hashtable<>();
        days.put(1,"Sunday");
        days.put(2,"Monday");
        days.put(3,"Tuesday");
        days.put(4,"Wednesday");



        for(Map.Entry entry : days.entrySet()){
            System.out.println(entry.getKey() + "," + entry.getValue());
        }

    }
}
