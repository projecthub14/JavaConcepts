package com.example.concurrency.ProducerConsumer;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.concurrency.ProducerConsumer.ProducerConsumerMain.EOF;

//using lock : 1.we can check for number of threads waiting for lock
// 2. add fairness to Reentrent lock to say thread is waiting for long time
//other operations check locks.lock

//instead of synchronisation we can use classes that implements
// java.util.concurrent.locks.lock interface

//we get lang.Error: Maximum lock count exceeded
// in consumer we keep getting lock and didnt release lock in buffer.isEmpty()
//.unlock is not executed at all

//lock and unlock are scattered around and looks messy
// here we are not using locks in recommended way

//we need to enclose critical sections of code in----- try finally block
//reason : 1. we need to call unlock in one place
//2. it could be possible for code within critical throw exeception
//that we are not explicitly handling
//3. also release locks we are holding in that scenario





public class ProducerConsumerLocks {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        //1. if a thread is already holding a reentrant lock when it reaches a
        //code that requires same lock it can continue executing
        //2. it doesnot need to obtain lock again
        //3. not all implementation of lock interface are reentrant
        //4. all threads have to compete for same lock to prevent thread interference

        ReentrantLock bufferLock = new ReentrantLock();

        MyProducerLock producer = new MyProducerLock(buffer, ThreadColor.ANSI_GREEN,bufferLock);
        MyConsumerLock consumer1 = new MyConsumerLock(ThreadColor.ANSI_PURPLE,buffer,bufferLock);
        MyConsumerLock consumer2 = new MyConsumerLock(ThreadColor.ANSI_CYAN,buffer,bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();



    }
}

//we need to remove thread interference - we need to add synchronized to buffer - arrayList is not threadsafe


class MyProducerLock implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerLock(List<String> buffer,String color,ReentrantLock bufferLock){
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }



    @Override
    public void run() {

        Random random = new Random();
        String[] nums = {"1","2","3","4","5"};

        for(String num : nums){
            try{
                System.out.println(color + "Adding...." + num);
                bufferLock.lock();

                //add synchronized to buffer otherwise producer will be suspended in middle of adding to buffer
                //then consumer's get ot remove call , integrity of arraylist might be compromised
                try {
                    buffer.add(num);
                } finally{
                    bufferLock.unlock();
                }




                //producer sleeps after adding string
                //usually consumer thread gets chance at that point
                //to remove last string added befroe producer thread itself runs again



                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException exception){
                System.out.println("Producer was interupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting");
        //same like synchronized block
        bufferLock.lock(); //acquire lock

//        buffer.add("EOF");
//        bufferLock.unlock(); //release lock --> it doesnot happen automatically

        try {
            buffer.add("EOF");
        } finally{
            bufferLock.unlock();
        }


    }

}

//arraylist isnt thread-safe
//its possible that one thread is suspended in middle of running the remove method
//before the item has actually removed from arrayList


//there is no correct order in which consumer thread removes data
//in each run different output


class MyConsumerLock implements Runnable {

    private String color;
    private List<String> buffer;
    private ReentrantLock bufferLock;

    public MyConsumerLock(String color , List<String> buffer,ReentrantLock bufferLock){
        this.color = color;
        this.buffer = buffer;
        this.bufferLock = bufferLock;
    }

    @Override
    //thread can test to see if lock is availble

    public void run() {
        int counter = 0;

        while(true){
            //we dont want producer or anotherconsumer to change the arraylist once a consumer
            // thread checked whether buffer is empty
            //all calls to arraylist methods are running as unit collectively in this critical section

            //check if lock is available
            if(bufferLock.tryLock()){
                try {
                    if(buffer.isEmpty()){
                        //bufferLock.unlock(); // now added to remove error
                        continue; // here bufferLock.unlock() is not executed
                    }
                    //thread as tried so many times to get lock
                    System.out.println(color + "The Counter = " + counter);
                    counter = 0;
                    //consumer 1 removes string from buffer
                    //consumer 2 try to access same string from buffer
                    if(buffer.get(0).equals(EOF)){
                        System.out.println(color+ " Exiting");
                        //no needed in try finally as we get error when we try to unlock
                        //bufferLock.unlock(); // now added to remove error
                        break;
                    }
                    // both consumers can try to remove same string so results in error
                    else{
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }

                } finally {
                    //make sure we unlock only once
                    bufferLock.unlock();
                }

            }
            else {
                counter++;
            }






        }



    }
}
