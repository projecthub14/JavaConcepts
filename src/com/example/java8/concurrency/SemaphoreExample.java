package com.example.java8.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(5);

        Runnable longRunningTask = () -> {
            boolean permit = false;
            try{
                permit = semaphore.tryAcquire(1, TimeUnit.SECONDS);
                if(permit){
                    System.out.println("Semaphore Acquired");
                    Thread.sleep(5000);
                }
                else{
                    System.out.println("Coulnot acquire Semaphore");
                }
            }
            catch (Exception e ){
                System.out.println(e);
            }
            finally {
                if(permit)
                semaphore.release();
            }
        };

        IntStream.range(0,10).forEach(i -> executor.submit(longRunningTask));
        executor.shutdown();


    }
}
