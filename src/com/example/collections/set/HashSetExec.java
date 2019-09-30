package com.example.collections.set;

import com.example.collections.list.Car;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetExec {

    public static void main(String[] args) {
        HashSet<Car> hashSet = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            hashSet.add(new Car("H-" + i));
        }

        Iterator<Car> iterator = hashSet.iterator();
        //not in same order as we inserted
        //allow unique elements
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //returns false as its already present
        //we implemented equals and hashcode so it understands
        System.out.println(hashSet.add(new Car("H-" + 3)));
    }
}
