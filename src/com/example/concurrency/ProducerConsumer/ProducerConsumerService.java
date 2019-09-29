package com.example.concurrency.ProducerConsumer;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.concurrency.ProducerConsumer.ProducerConsumerMain.EOF;


//1. it is in java.util.concurrent package
//2. we use implementation of this interface to manage threads for us
//so we dont have to explicitly create and start threads
//3. implemntation provided by jvm manages things like thread scheduling and also
//optimize the creation of threads which is expensive interms of performance and memory
//4. we still have to provide runnable objects to the service , obviously
// we have to code the tasks we want to execute on background threads
//executive service implementation allow us to focus on code we want to run
//without fuss on managing threads and their lifecycles
//5. we create implementation of executive service and give it tasks we want to run

//6. executive service implementation make use of thread pools ->
//thread pool is managed set of threads --> reduces overhead of thread creation
//especially in applications that use large number of threads
//thread pool may also limit number of threads that are active running
//7. wehen we ask service to run a task it wont be able to run it straight away
//eg: if maximum number of threads is 20 there will be 20 active threads working on task
//in that case the task have to wait in service queue until one of active thread actually terminates

//8.Executive service interface extends executor interface which has one method
//execute


public class ProducerConsumerService {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();

        ReentrantLock bufferLock = new ReentrantLock();

        //create executor instance
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MyProducerService producer = new MyProducerService(buffer, ThreadColor.ANSI_GREEN,bufferLock);
        MyConsumerService consumer1 = new MyConsumerService(ThreadColor.ANSI_PURPLE,buffer,bufferLock);
        MyConsumerService consumer2 = new MyConsumerService(ThreadColor.ANSI_CYAN,buffer,bufferLock);

//        new Thread(producer).start();
//        new Thread(consumer1).start();
//        new Thread(consumer2).start();

        //give the task to run
        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        //number of threads in pool affects order of running callable
        //if there are enough threads then callable runs first

        //if we want to recieve a value back from thread then use submit method on callable object
        //callable interface call() method can return value that is object of type Future
        Future<String> future = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_BLUE + "I'm being printed for Callable class");
                return "This is callable result";
            }
        });

        try{
            //dont use in UI application
            //this blocks untill result is available
            System.out.println(future.get());
        }
        catch (ExecutionException e){
            System.out.println("Something went wrong");
        }
        catch (InterruptedException e){
            System.out.println("Thread running the task was interrupted");
        }

        //even after main thread is terminated executive service doesnot shutdown
        //we need to do explicitly
        executorService.shutdown();




    }
}


class MyProducerService implements Runnable {

    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerService(List<String> buffer,String color,ReentrantLock bufferLock){
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

                  try {
                    buffer.add(num);
                } finally{
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException exception){
                System.out.println("Producer was interupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting");

        bufferLock.lock();

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


class MyConsumerService implements Runnable {

    private String color;
    private List<String> buffer;
    private ReentrantLock bufferLock;

    public MyConsumerService(String color , List<String> buffer,ReentrantLock bufferLock){
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
