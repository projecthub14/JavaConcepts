package com.example.collections.list;


import java.util.ArrayList;
import java.util.Iterator;

//Added in sequence
//start from index 0
//implements List and Collection interface
// most methods are defined in Collection and List interface -> from list interface have parameter index
public class ArrayListExec {


    public static void main(String[] args) {
        ArrayList<Car> carsList = new ArrayList<Car>();
        Car c = new Car("1234");

        carsList.add(c);
        System.out.println(carsList.size());

        //add(int index ,E e)

        //error : IndexOutOfBoundxception as size : 1
        //carsList.add(4,new Car("Hqwq"));

        for (int i = 0; i <5 ; i++) {
            carsList.add(i,new Car("H"+i));
        }
        carsList.add(4,new Car("Hqwq"));

        //Iterator
        Iterator<Car> carIterator = carsList.iterator();

        while(carIterator.hasNext()){
            System.out.println(carIterator.next());
        }

        //clear() -> removes all elements

        //clone
        ArrayList<Car> carsList2 = (ArrayList<Car>) carsList.clone();
        //Iterator
        Iterator<Car> carIterator2 = carsList.iterator();

        while(carIterator2.hasNext()){
            System.out.println(carIterator2.next());
        }

        //contains(Object o) -> executes equals and hashcode implementation
        //of particular element
        //if not present then objects equals and hascode is compared since they are different returns false

        System.out.println(carsList.contains(new Car("H1")));

        //get(index)
        System.out.println(carsList.get(4));

        //if(carsList.size() ==0) //bad practice
        //use
        //if(carsList.isEmpty()) //best practice

        //Loop and size

        //for (int i = 0; i <carsList.size() ; i++) bad practice
       // for (int i = 0,n=carsList.size(); i < n; i++) good practice

        //indexOf
        System.out.println(carsList.indexOf(new Car("H1")));

        // ensureCapacity

        ArrayList<Car> carsList3 = new ArrayList<Car>();
        carsList.ensureCapacity(300); //reduce performance when array tries to duble size during add(e)
        long  startTime = System.nanoTime();
        for (int i = 0; i <100 ; i++) {
            carsList3.add(new Car("H"+(i+10)));
            
        }
        System.out.println("Elapsed Time ENSURE CAPACITY " + (System.nanoTime() - startTime));
    }
}

//Arraylist and Vector(both have same methods) :

//1. They both offer same functionality
//a. Vector is first implementation of list interface introduced in java 1.0
//ArrayList introduced in java 1.2
//b. Vector is thread safe and ArrayList is not - arraylist can be manipulated
//by multiple threads wheras for vector only one thread can modify at given time
//Arraylist is not thread safe so it performs better than vector
//c.arraylist is better -> when we try to add element -> arraylist tries to grow by half size
//whereas vector double the size -> scales beyond the density
//d. vector can be used in single threaded applications -> where thread safety is important
//e. arrayList in multi thread application

//Arraylist - making thread safe using collections utility class
//List list = Collections.synchronizedList(new ArrayList())

//synchronized(list){}



