package com.example.concurrency.deadlock;


//Common problems when working with threads :
//1. we use synchronization or locks to prevent critical section of code from
//thread interference
//but we cause more problems doing that
// ---> first common pitfall is deadlock
//Deadlock occurs when two or more threads are blocked on locks and
//every thread that is blocked is holding lock that another block thread want

//eg : thread1 is holding lock1 and waiting to acquire lock2
//but thread2 is holding lock2 and waiting to acquire lock1
//so none of waiting threads will ever run

import static com.example.concurrency.deadlock.DeadLockMain.lock1;
import static com.example.concurrency.deadlock.DeadLockMain.lock2;

//one way to reduce deadlock is to obtain locks in same order
//ie thread2 to obtain loacks -> first lock1 and then lock2

public class DeadLockMain {

    public static Object lock1 = new Object();
    public static Object lock2 = new Object();


    public static void main(String[] args) {

        new Thread1().start();
        new Thread2().start();



    }


    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1 : has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exc) {

                }
                System.out.println("Thread1 waiting for lock2 ");
                synchronized (lock2) {
                    System.out.println("Thread1 has lock1 and lock2");
                }
                System.out.println("Thread1 : Released Lock2");
            }
            System.out.println("thread1 : released lock1 and exiting");
        }

    }

    private static class Thread2 extends Thread {
        public void run() {
            //synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("Thread2 has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exc) {

                }


            System.out.println("Thread2 waiting for lock2");
               // synchronized (lock1) {
            synchronized (lock2) {
                try {
                    System.out.println("Thread2 has for lock1 and lock2");
                    Thread.sleep(100);
                } catch (InterruptedException exc) {

                }
            }
            System.out.println("Thread2 released  lock2");
        }

        System.out.println("Thread2 released  lock1");

        }
    }
}