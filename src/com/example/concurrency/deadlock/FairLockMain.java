package com.example.concurrency.deadlock;
import com.example.concurrency.basicThreadFunctions.ThreadColor;

import java.util.concurrent.locks.ReentrantLock;

//Fair lock is first come first serve
//we dont have starvation problem
//All threads runs in below order with no thread in starvation and each thread
//gets fair chance

//threads are interleaving much more

//Prioriry 6 : runCount = 1
//Prioriry 8 : runCount = 1
//Prioriry 2 : runCount = 1
//Prioriry 4 : runCount = 1
//Prioriry 10 : runCount = 1
public class FairLockMain {

    //lock is class object which is only one instance of the object all threads are competing for that single lock
    private static ReentrantLock lock = new ReentrantLock(true); // true means fair lock -> first come first served ordering  to get lock -> performance impacted
    //where it should have layer to check which thread next in line to get lock


    public static void main(String[] args) {

        Thread t1 = new Thread(new Worker(ThreadColor.ANSI_RED), "Prioriry 10");
        Thread t2 = new Thread(new Worker(ThreadColor.ANSI_PURPLE), "Prioriry 8");
        Thread t3 = new Thread(new Worker(ThreadColor.ANSI_BLUE), "Prioriry 6");
        Thread t4 = new Thread(new Worker(ThreadColor.ANSI_GREEN), "Prioriry 4");
        Thread t5 = new Thread(new Worker(ThreadColor.ANSI_CYAN), "Prioriry 2");


        t1.setPriority(10);
        t1.setPriority(8);
        t1.setPriority(6);
        t1.setPriority(4);
        t1.setPriority(2);

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


                lock.lock();
                    try{
                        System.out.format(threadColor + "%s : runCount = %d\n" , Thread.currentThread().getName() , runCount++);
                        //execute critical section of code
                    }
                    finally {
                        lock.unlock();
                    }

                }
            }

        }
    }

    //if in scenario if you dont care if threads are waiting for long or in
//application where threads read data from db . we dont care which thread pulls up data
//here synchronized blocks will be preferable

//Livelock is similar to Deadlock
//similar to deadlock but instead of blocked they are constantly active
//usually waiting for all other threads to complete their task , now since all
//threads are waiting for other threads to complete none of them can actually progress

//eg : lets say threadA will loop until threadB complete tasks and
//threadB will loop until threadA complete tasks
//threadA and threadB can get into state in which they are both looping/waiting for
//other thread to complete task

//This is livelock - the threads will never progress but they are blocked



