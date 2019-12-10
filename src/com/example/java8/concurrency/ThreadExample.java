package com.example.java8.concurrency;

public class ThreadExample {

    public static void main(String[] args) {
        Runnable task = () -> {
            try{
                String threadName = Thread.currentThread().getName();
                //System.out.println("Hello " + threadName);
                System.out.println("Foo " + threadName);
                Thread.sleep(3000);
                System.out.println("Bar " + threadName);
            }
            catch (InterruptedException e){
                System.out.println("Exception  " + e);
            }


        };

        task.run();

        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Done");
    }
}
