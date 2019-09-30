package com.example.collections.set;

import com.example.collections.list.Car;
import com.example.collections.list.CustomComparator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetExec {

    public static void main(String[] args) {
        Set<Car> treeSet = new TreeSet<Car>();

        for (int i = 0; i <100 ; i++) {
            Car c = new Car("H- " + i);
            c.setPrice((int)(Math.random()*100) + 1);
            treeSet.add(c);
        }

        Car c3 = new Car("H-499");
        c3.setPrice(50);
        treeSet.add(c3);

        Iterator<Car> carIterator = treeSet.iterator();
        while (carIterator.hasNext()){
            Car c = carIterator.next();
            System.out.println( c + "Price " + c.getPrice());
        }

        System.out.println("First :" + ((TreeSet<Car>) treeSet).first());
        System.out.println("Last :" + ((TreeSet<Car>) treeSet).last());

        System.out.println("HeadSet ...");

       // headSet(E element) returns SortedSet<E>  so iterate on this set
        Iterator<Car> headIterator = ((TreeSet<Car>) treeSet).headSet(c3).iterator();
        while (headIterator.hasNext()){
            Car c2 = headIterator.next();
            System.out.println(c2 + " Price : " + c2.getPrice());
        }

        System.out.println("TailSet ...");
        // tailSet(E element) returns SortedSet<E>  so iterate on this set
        Iterator<Car> tailIterator = ((TreeSet<Car>) treeSet).tailSet(c3).iterator();
        while (tailIterator.hasNext()){
            Car c2 = tailIterator.next();
            System.out.println(c2 + " Price : " + c2.getPrice());
        }

        System.out.println("Navigable Set methods ...");
//fetch closest match
        System.out.println("Ceiling ...");
       // returns least element in Set if match is not found
        System.out.println(((TreeSet<Car>) treeSet).ceiling(c3));

        System.out.println("Flooring ...");
       // returns greatest element in Set if match is not found
        System.out.println(((TreeSet<Car>) treeSet).floor(c3));

        System.out.println("Higher ....");
        // returns least element that is higher thsn given element
        System.out.println(((TreeSet<Car>) treeSet).higher(c3)); //next element

        System.out.println("Lower ....");
        // returns highest element that is lower thsn given element
        System.out.println(((TreeSet<Car>) treeSet).lower(c3));//previous element

        //want to use custom comparator -> implement Comparator
        TreeSet<Car> treeComparator = new TreeSet<>(new CustomComparator());
        treeComparator.addAll(treeSet);

        System.out.println("After sorting in comparator ....");
        Iterator<Car> treeIterator = treeComparator.iterator();
        while(treeIterator.hasNext()){
            Car c =treeIterator.next();
            System.out.println(c + "Price : " + c.getPrice());
        }
    }
}
