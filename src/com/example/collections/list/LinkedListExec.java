package com.example.collections.list;

//ordered list
//implements list interface , queue , dQueue interface

import java.util.Iterator;
import java.util.LinkedList;


//difference between remove() and poll() -> if result is null remove gives Nullpointer
//whereas poll gives null
public class LinkedListExec {

    public static void main(String[] args) {
        //we can add from front or end of list
        //Queue -> adds element at end , access element from head , no access to elements between

        //list interface methods are same as ArrayList implementation
        //queue , dQueue interface methods
        //1. addFirst(e) ,addLast(e) , getFirst() , getLast() ,removeFirst(), removeLast()

        LinkedList<Car> linkedList = new LinkedList<>();
        for (int i = 0; i <100 ; i++) {
            linkedList.add(new Car("H"+i));
        }


        linkedList.addFirst(new Car("H-First"));
        linkedList.addLast(new Car("H-Last"));

        //for list interface
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());

        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeLast());

        //peek() (take a quick look) for queue interface - with no peek index

        System.out.println(linkedList.peek());
        System.out.println(linkedList.peekFirst());
        System.out.println(linkedList.peekLast());

        //poll() -> retrieves and removes
        System.out.println(linkedList.poll());
        System.out.println(linkedList.pollFirst());
        System.out.println(linkedList.pollLast());

        //offer() -> add
        //offer(e) - adds specific element at tail
        //offerFirst(e) -> add specific elemetn to first
        //offerLast(e) -> add specific element at last
        // descendingIterator() -> iterates from last element - >dQueue Iterface

        Iterator<Car> carLastIterator = linkedList.descendingIterator();
        while(carLastIterator.hasNext()){
            System.out.println(carLastIterator.next());
        }



    }

}
