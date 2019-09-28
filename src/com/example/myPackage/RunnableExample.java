package com.example.myPackage;

public class RunnableExample {


    public static void main(String[] args) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Printed inside Runnable");
            }
        });

        myThread.run();

        //or

        //this works because Runnable has single method ,
        //if it had more than one method , we have to write lamda function of that type

        Thread myLambdaThread = new Thread(() ->  System.out.println("Printed inside Lambda Runnable"));
        myLambdaThread.run();

    }


}

