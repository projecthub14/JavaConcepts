package com.example.collections.set;

import com.example.collections.list.Car;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExec {

    public static void main(String[] args) {
        Set<Car> linkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i < 100; i++) {
            linkedHashSet.add(new Car("H-" + i));
        }

        Iterator<Car> iterator = linkedHashSet.iterator();
        // in same order as we inserted
        //ordering based on insertion criteria
        //doesnot have any index
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
