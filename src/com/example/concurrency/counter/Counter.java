package com.example.concurrency.counter;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

//heap is application memory all threads share
//every threads have thread stack and thats memory of individual thread


public class Counter {

    public static void main(String[] args) {

        //thread will have seperate heap -  no interference
//        CountDown countDownObj1 = new CountDown();
//        CountDown countDownObj2 = new CountDown();
        //not possible in real world situation - bank account -  to maintain
        //integrity of data
       // 1. bank balance - updated by several threads - automatic deposit
        //schedule build payment, online banking
        //prevent race condition - synchronization - when method is synchronized
        //only one thread execute it at time - other threads are suspended

        CountDown countDown = new CountDown();


        CountDownThread t1 = new CountDownThread(countDown);
        t1.setName("Thread 1");

        CountDownThread t2 = new CountDownThread(countDown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();


    }
}

//we can synchronize a method or block of statements
//to synchronize block of statement a thread has to acquire lock from object and
//release lock once done so that waiting thread will get lock
//every java object hace intrinsic lock
class CountDown{

       //we cant use primitive type to synchronize block
       // as they dont have intrinsic locks
        private int i;

        //synchronized keyword ---------- only one thread can run at a time
        public /*synchronized*/ void doCountDown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1" :
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default :
                color = ThreadColor.ANSI_GREEN;
        }
        //using local variable order of 2 thread execution is not guaranteed
            //localvariables are stored in thread stack
//        for (int i = 10; i >0 ; i--) {
//
//            System.out.println(color + Thread.currentThread().getName()+ " : " + i);
//        }
            //this is called race condition ----- two or more threads are writing shared resource
            //instance variable are in heap
            //first time when thread run they have same value 10
            // after that both threads are sharing variable by printing alternate values
            // as one thread modify one value and other thread sees new value

            //suspension point -> happens when executing syso -> which has I/o +  string concatenation
            //because of this when one thread is taking time to write othr thread would have already executed

            //no way to predict which threads prints the number and there will interference
            //we are using local variable synchronize -> thread stack
            // so each thread gets their own copy of lock so we need to use object that will share between threads
            //so they compete for same lock -- dont use local variables

            //one exception is with String objects as they are reused within jvm i mean
            //jvm uses string pool for allocating string objects
            // synchronized (color){

            //they dont interleave - we can use static methods
            //synchronize using countdown object that threads share
            //we can synchronize static methods and can use static objects ->
            // so lock is owned by the class object associated with objects class

            //synchronization is reentrant -> if thread acquires an object lock and within synchronized code
            //it calls a  method that's using same object to synchronize some code

            //critical section -> referes to code thats referencing shared resource like a variable
            // only one thread at a time should be able to execute a critical section

            //thread safe -> class or method is thread safe meaning the developer has
            //synchronized all the critical sections within the code so that we as
            //developer dont have to worry about thread interference.

            //when synchronizing - just synchronize the code that needs to be
            //synchronized - just synchronize critical sections of code


            synchronized (this){

                for (i = 10; i >0 ; i--) {

                    System.out.println(color + Thread.currentThread().getName()+ " : " + i);
                }

            }

    }
}

class CountDownThread extends Thread{

    private CountDown threadCountDown;

    public CountDownThread(CountDown countDown){
        this.threadCountDown = countDown;
    }

    public void run(){
        threadCountDown.doCountDown();
    }

}
