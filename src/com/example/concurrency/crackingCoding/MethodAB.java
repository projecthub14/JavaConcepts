package com.example.concurrency.crackingCoding;


//only one synchronized method per instance of object can be executed.
//
public class MethodAB {

    public synchronized void methodA(){
        System.out.println("Method A ");
    }

    public void methodB(){
        System.out.println("Method B");
    }

    public static void main(String[] args) {

        MethodAB methodAB = new MethodAB();
        new Thread(new Runnable() {
            @Override
            public void run() {
                methodAB.methodA();
                methodAB.methodB();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                methodAB.methodA();
                methodAB.methodB();
            }
        }).start();

    }
}
