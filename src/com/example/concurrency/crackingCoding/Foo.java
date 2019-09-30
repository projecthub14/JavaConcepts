package com.example.concurrency.crackingCoding;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {

    public Lock lock;
    public Foo(){
        lock = new ReentrantLock();
    }

    public void first(){
        lock.lock();
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){

        }
        System.out.println("I am first method");
        lock.unlock();
    }
    public void second(){
        lock.lock();
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){

        }
        System.out.println("I am second method");
        lock.unlock();
    }

    public void third(){
        lock.lock();
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){

        }
        System.out.println("I am third method");
        lock.unlock();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                foo.first();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                foo.second();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                foo.third();
            }
        }).start();
    }
}
