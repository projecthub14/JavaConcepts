package com.example.java8.concurrency;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutorService {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            //System.out.println("Hello " + threadName);
            System.out.println("Foo " + threadName);
        });

        executor.shutdown();

    }
}
