package com.example.lambda.unit3;

public class MethodReferenceExample1 {


    public static void main(String[] args) {


        // if lambda arguments and method call arguments match
        // then we have new syntax that is "method reference"

       // Thread thread = new Thread(() ->  printMessage());
        // this is Method Reference same as before

        //MethodReferenceExample1::printMessage === () ->  printMessage()
        //this is static method - > call method by ClassName
        Thread thread = new Thread(MethodReferenceExample1::printMessage); // () -> method()
        thread.start();
    }

    public static void printMessage(){
        System.out.println("Hello");

    }
}
