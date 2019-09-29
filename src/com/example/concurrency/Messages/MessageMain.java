package com.example.concurrency.Messages;

import java.util.Random;

public class MessageMain {

    public static void main(String[] args) {
        Message message = new Message();

        (new Thread(new Writer(message))).start();

        (new Thread(new Reader(message))).start();


    }
}


class Message {

    private String message;
    private boolean empty = true;

    //when one thread starts looping other one cant chnage value of
    //empty because the thread is blocked
    //only one synchronized method can run at a time
    //so the thread that is looping is holding the message  object lock and other thread is blocked

    //this is called deadlock

    //then we use wait , notify , notify all method
    //when thread is in wait it will suspend execution and relase lock its holding
    //until other thread notify that something important happened
    //other thread issues notification using notify or notify all methods


    //if not empty read and set empty to true
    public synchronized String read(){

        //while loop to check after waiting is suspended by notify
        //has changed value of empty
        while(empty){

           try{
               wait();
           }
           catch (InterruptedException execption){

           }

        }
        empty = true;
        notifyAll(); // we cant notify a specific thread

        return message;
    }

    //if empty write the message and set empty to false

    //other thread cannot modify content as only one synchronized method can run
    public synchronized void write(String message){
        while(!empty){
            try{
                wait();
            }
            catch (InterruptedException exception){

            }
        }
        empty = false;
        notifyAll(); // this allows thread that is waiting to proceed
        this.message = message;
    }

}

//Producer
class Writer implements Runnable {
    private Message message;


    public Writer(Message message) {
        this.message = message;
    }

    @Override
    public void run() {

        String messages[] = {
                "Humpty Dumpty",
                "Twinkle Twinkle",
                "Good to teach",
                "Good try"
        };

        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException exception) {

            }
        }
        message.write("Finished");


    }

}

class Reader implements Runnable{

    private Message message;

    public Reader(Message message){
        this.message = message;
    }

    @Override
    public void run() {

        Random random = new Random();

        for(String latestMessage = message.read();!latestMessage.equals("Finished");latestMessage = message.read()){
            System.out.println("Message is " + latestMessage);
            try{
                Thread.sleep(random.nextInt(2000));
            }
            catch (InterruptedException exception){

            }
        }



    }
}



