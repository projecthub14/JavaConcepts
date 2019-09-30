package com.example.concurrency.liveLock;


//create 2 worker threads that share a resource
//when they see other thread is active they give the resource to other thread
//wait for it to finish using that resource
public class PoliteWorkerMain {

    public static void main(String[] args) {

        final Worker worker1 = new Worker("Worker 1",true);
        final Worker worker2 = new Worker("Worker 2" , true);

        final SharedResource sharedResource = new SharedResource(worker1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource,worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource,worker1);
            }
        }).start();

        //neither of them are blocked , they are both are constantly running or live -> live lock
        //anytime threads have to wait for other threads to complete they dont block which they wait -> live stock

        //other potential problem that can rise in multi threaded application -> slip condition -> this is specific type
        //of reace condition(aka thread interference) --> it can occur when thread can be suspended between reading a condition
        //and acting on it

        //slipped condition is particular type of race condition -> solution is same like any type of thread interference :
        //.1 use synchronized block 2. locks to synchronize critical section of code
        //if code is already synchronized then placement of synchronization may be causing the problem

        //when using locks th order in which locks are acquired also cause slipped condition


    }


}


