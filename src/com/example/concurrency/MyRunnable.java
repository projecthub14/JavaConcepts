package com.example.concurrency;

import static com.example.concurrency.ThreadColor.ANSI_RED;

public class MyRunnable implements Runnable {
    @Override
    public void run() {

        System.out.println(ANSI_RED + "Hello from MyRunnable implementation of Run");

    }
}
