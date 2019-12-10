package com.example.java8.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ReEntrantLock {

    ReentrantLock lock = new ReentrantLock();
    int count = 0;

    public void increment(){
       lock.lock(); //acquires lock
       try{
           count++;
       }
       finally {
           lock.unlock(); //release lock
       }
    }

    public static void main(String[] args) {
        SynchronizedExample ex = new SynchronizedExample();

        ExecutorService service = Executors.newFixedThreadPool(2);
        IntStream.range(0,1000)
                .forEach(i -> service.submit(() -> ex.increment()));



        service.shutdown();
        System.out.println(ex.count);
    }
}
