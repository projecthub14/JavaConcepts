package com.example.java8.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureService {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            try{
                Thread.sleep(3000);
                return 123;
            }
            catch (InterruptedException e){
                throw new IllegalStateException("task interrupted" + e);
            }
        };


        Future<Integer> future = executorService.submit(task);
        System.out.println("Is Future done" + future.isDone());

        Integer result = 0;
        try {
            result = future.get();
        }
        catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Is Future done" + future.isDone());
        System.out.println("Result " + result);

        executorService.shutdown();

    }
}
