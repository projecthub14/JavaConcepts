package com.example.concurrency.ProducerConsumer;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//before java 1.5 there was no java.util.concurrent package ,  so synchronization
//was the only way to deal with thread interference
//Drawback of synchronisation
//1. Threads that are blocked waiting to execute synchronized code can't be interupted
//once they are blocked they are stuck there until they get the lock for the object
//the code is synchronizing on/
//2. Synchronized block must be within same method -> we cannot start
// synchronized block in one method and end the synchronization block in other method
//3. we cannot test to see if an objects intrinsic lock is available
//or find out any other information about that lock and also if block
//is not available we cannot timeout after we waited for lock for a while
//4. when we reach beginnning of synchronized block we can either get
//the lock and continue executing or block at that line of code until we get lock
//5. if multiple threads are waiting to get lock its not first come first served
//there isnt any set order in which jvm will choose next thread to get lock
//so first thread that blocked could be last thread to get lock

//instead of synchronisation we can use classes that implements
// java.util.concurrent.locks.lock interface



import static com.example.concurrency.ProducerConsumer.ProducerConsumerMain.EOF;

public class ProducerConsumerMain {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_GREEN);
        MyConsumer consumer1 = new MyConsumer(ThreadColor.ANSI_PURPLE,buffer);
        MyConsumer consumer2 = new MyConsumer(ThreadColor.ANSI_CYAN,buffer);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();



    }
}

//we need to remove thread interference - we need to add synchronized to buffer - arrayList is not threadsafe


class MyProducer implements Runnable {

    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer,String color){
        this.buffer = buffer;
        this.color = color;
    }



    @Override
    public void run() {

        Random random = new Random();
        String[] nums = {"1","2","3","4","5"};

        for(String num : nums){
            try{
                System.out.println(color + "Adding...." + num);
                //add synchronized to buffer otherwise producer will be suspended in middle of adding to buffer
                //then consumer's get ot remove call , integrity of arraylist might be compromised
                synchronized(buffer) {
                    buffer.add(num);
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
        synchronized (buffer){
            buffer.add("EOF");
        }


    }

}

//arraylist isnt thread-safe
//its possible that one thread is suspended in middle of running the remove method
//before the item has actually removed from arrayList


//there is no correct order in which consumer thread removes data
//in each run different output



class MyConsumer implements Runnable {

    private String color;
    private List<String> buffer;

    public MyConsumer(String color , List<String> buffer){
        this.color = color;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){
            //we dont want producer or anotherconsumer to change the arraylist once a consumer
            // thread checked whether buffer is empty
            //all calls to arraylist methods are running as unit collectively in this critical section
            synchronized (buffer){

                if(buffer.isEmpty()){
                    continue;
                }
                //consumer 1 removes string from buffer
                //consumer 2 try to access same string from buffer
                if(buffer.get(0).equals(EOF)){
                    System.out.println(color+ " Exiting");
                    break;
                }
                // both consumers can try to remove same string so results in error
                else{
                    System.out.println(color + "Removed " + buffer.remove(0));
                }

            }

        }



    }
}
