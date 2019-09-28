package com.example.concurrency;

import static com.example.concurrency.ThreadColor.*;

public class ThreadMain {

    //order of execution is up to sysem schedular
    public static void main(String[] args) {

        System.out.println(ANSI_PURPLE + "Hello from main thread");

        //1. create instance of named thread class
        AnotherThread anotherThread = new AnotherThread();

        anotherThread.setName("==== Another Thread =====");



        //output is "Hello from main"
       // anotherThread.run(); // this will not run on another thrad but from main thread

        //enables jvm to run run method from thread
        anotherThread.start();

        //anonymous class
        new Thread(){
            @Override
            public void run() {
                System.out.println(ANSI_GREEN +"Hello from anonymous class ");
            }
        }.start();


        // create new thread based on my runnable interface

        //1. this is preferred way of implementing thread
        //2. many methods in Java API  have runnable passed to them
        Runnable runnable = new MyRunnable();
        Thread myRunnableThread = new Thread(runnable);
        myRunnableThread.start();

        //with lambda expression this is more commanly used

        //we just implement run method and we dont call run method directly
        // jvm will call it for us by start method
        //if we call run method then instead of calling run method on new thread
        // its going to call run method on same thread that called the run method

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED+"Hello from   anonymous class Runnable implementation");

                try {
                    //here myRunnable thread waits and join back after
                    //anotherthread is done execution

                    //we can have overload method to method how long to wait join(millisec)
                    anotherThread.join();
                    System.out.println(ANSI_RED+ "AnotherThread terminated , or timedOut I am running again");
                }
                catch (InterruptedException exception){
                    System.out.println(ANSI_RED + "I couldnt wait after all I was interruped");
                }
            }
        }).start();

        //we are calling interupt on thread which is sleeping
        //so it gives exception
       // anotherThread.interrupt();

        //order of execution is up to sysem schedular
        System.out.println(ANSI_PURPLE + "Hello again from main thread");

        //we get error since we have to start thread once on any instance
        //anotherThread.start();


    }
}
