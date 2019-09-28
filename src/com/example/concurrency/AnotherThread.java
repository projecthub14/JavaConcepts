package com.example.concurrency;

import static com.example.concurrency.ThreadColor.ANSI_BLUE;

//jvm has to call  underlying operation system to put thread to sleep
// it is possible that operating system may not supprt granularity
// ( forex: nano seconds - one of paramter of sleep)

//there is no guarantee that threads will sleep for 3 sec
//it can be interupped by another thread

public class AnotherThread extends Thread {
    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());
        try{
            //make thread to sleep
            Thread.sleep(3000);
        }
        catch (InterruptedException exc){
            System.out.println(ANSI_BLUE + "Another thread woke me up");
            return;
        }

        System.out.println(ANSI_BLUE + " Three seconds have passed and I'm awake");
    }
}
