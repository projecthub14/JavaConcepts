package com.example.concurrency.deadlock;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

public class StarvationMain {

    //lock is class object which is only one instance of the object all threads are competing for that single lock
    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Prioriry 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Prioriry 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Prioriry 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Prioriry 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Prioriry 2");

        // we are giving suggessiton to os to pick thread to give lock
        //but decision is upto operating system on priority

        //results keep changing on each run

        //deadlocks - order in which locks are required is important
        //setting priority can leads to starvation - in starvation which thread gets lock when lock is available is important
        //ideally would like things to be first come first serve


//        t1.setPriority(10);
//        t1.setPriority(8);
//        t1.setPriority(6);
//        t1.setPriority(4);
//        t1.setPriority(2);

        t3.start();
        t2.start();
        t5.start();
        t4.start();
        t1.start();
    }

    private static class Worker implements Runnable{

        private int runCount = 1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {

            for (int i = 0; i <100 ; i++) {
                synchronized (lock) {
                    System.out.format(threadColor + "%s : runCount = %d\n" , Thread.currentThread().getName() , runCount++);
                    //execute critical section of code


                }
            }

        }
    }
}
