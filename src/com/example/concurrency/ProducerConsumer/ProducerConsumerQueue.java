package com.example.concurrency.ProducerConsumer;

import com.example.concurrency.basicThreadFunctions.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.example.concurrency.ProducerConsumer.ProducerConsumerMain.EOF;


//1. now package contains thread safe queues which is perforect for producer
//2. all queue class implements blocking queue interface
//3. it processes elements in FIFO order





public class ProducerConsumerQueue {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

//        List<String> buffer = new ArrayList<>();
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

//        ReentrantLock bufferLock = new ReentrantLock();

        //create executor instance
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        MyProducerQueue producer = new MyProducerQueue(buffer, ThreadColor.ANSI_GREEN/*,bufferLock*/);
        MyConsumerQueue consumer1 = new MyConsumerQueue(ThreadColor.ANSI_PURPLE,buffer/*,bufferLock*/);
        MyConsumerQueue consumer2 = new MyConsumerQueue(ThreadColor.ANSI_CYAN,buffer/*,bufferLock*/);

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


class MyProducerQueue implements Runnable {

    private ArrayBlockingQueue<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducerQueue(ArrayBlockingQueue<String> buffer,String color/*,bufferLock*/){
        this.buffer = buffer;
        this.color = color;
        //this.bufferLock = bufferLock;
    }



    @Override
    public void run() {

        Random random = new Random();
        String[] nums = {"1","2","3","4","5"};

        for(String num : nums){
            try{
                System.out.println(color + "Adding...." + num);

                //inserts element has passed at tail of queue
                //if queue is full then it waits for queue to be available

                 buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            }
            catch (InterruptedException exception){
                System.out.println("Producer was interupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting");



        try {
            buffer.put("EOF");
        } catch (InterruptedException exec) {

        }


    }

}

//arraylist isnt thread-safe
//its possible that one thread is suspended in middle of running the remove method
//before the item has actually removed from arrayList


//there is no correct order in which consumer thread removes data
//in each run different output


class MyConsumerQueue implements Runnable {

    private String color;
    private ArrayBlockingQueue<String> buffer;
    private ReentrantLock bufferLock;

    public MyConsumerQueue(String color , ArrayBlockingQueue<String> buffer/*,bufferLock*/){
        this.color = color;
        this.buffer = buffer;
        //this.bufferLock = bufferLock;
    }

    @Override
    //thread can test to see if lock is availble

    //thread safe means one of our class methods will complete before
    //another thread can run a method in the class so producer only calls
    //put method and code following put doesnot depend on result of put
    //but in consumer code we are checking if queue is empty if it is isnt we
    //we peek at next element but consumer thread can be suspended between calling is empty and calling peek
    //while it is suspend other consumer thread can run and take next element from queue
    //and remove it .
    //when consumer thread runs again peek returns null
    //we have to add synchronization code when using thread safe classes like array blocking

    public void run() {

        while(true){

            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {

                        continue;
                    }
                    //thread as tried so many times to get lock
                    System.out.println(color + "The Counter = ");



                    //returns head of queue but not remove
                    //returns null if queue is empty
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + " Exiting");
                        break;
                    } else {
                        //retrieve and remove from head of queue
                        //if queue is empty then it waits until elements become available

                        System.out.println(color + "Removed " + buffer.take());
                    }

                } catch (InterruptedException exc) {


                }
            }

            }

        }

    }

