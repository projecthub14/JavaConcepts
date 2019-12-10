package com.example.java8.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SynchronizedExample {

    int count = 0;
    public  void increment() {
        synchronized(this){
            count = count + 1;
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
