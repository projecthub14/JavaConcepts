package com.example.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SortingExec {

    public static void main(String[] args) {

        ArrayList<Car> carsList = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            Car c = new Car("H-"+i);
            c.setPrice((int)(Math.floor(Math.random() * 100)) + 1);
            carsList.add(c);
        }

        Iterator<Car> carIterator = carsList.iterator();

        while(carIterator.hasNext()){
            Car c = carIterator.next();
            System.out.print(c.getRegistrationNumber() + " ");
            System.out.println("Price : " + c.getPrice() + " ");

        }

        //Collections.sort(carsList);
        //Custom sorting
        Collections.sort(carsList,new CustomComparator());



        System.out.println("After Sorting");

        Iterator<Car> afterCarIterator = carsList.iterator();
        while(afterCarIterator.hasNext()){
            Car c = afterCarIterator.next();
            System.out.print(c.getRegistrationNumber() + " ");
            System.out.println("Price : " + c.getPrice() + " ");

        }
    }
}
