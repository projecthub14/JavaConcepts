package com.example.lambda.unit1;


//doubleNumberFunction = public int double(int a) {
//           return a * 2;
//        }
 //1. remove public
//2. remove return type int
//3. remove function name as we refer lambda expression by literal
//4. add lamba "->" between argument list and opening brace
//5. add semicolon
//6. if lamba expression has one line we can skip braces and return type

//doubleNumberFunction =  (int a) -> a * 2;

// syntax is a. input arguments b. "->"  c.return value

//Lambdas are the functions which exists in isolation
//these functions are treated as values -> functions are entities which are passed around

//in Java 7 we nned to have classes/object to pass behaviour
//with Lambda we can pass action

//1. Lamdas enable functional programming
//2. Readable and concise code
//3. Easier-to-use APIs and libraries -> collection API .. more libraries use lambda
//4. Enables support parallel processing -> streams

public class Greeter {

    // accept a behaviour and perm that thing
    public void greet(Greeting greeting) {
       // System.out.println("Hello World!");
        greeting.perform();

    }

    public static <sout> void main(String[] args) {
        Greeter greeter = new Greeter();

        //helloworld is instance of HelloWorldGreeting Implementation
        Greeting helloWorld= new HelloWorldGreeting();

        // we are passing behaviour/class which has method to do action
        greeter.greet(helloWorld); // pass a behaviour // this is polymorphism


        // anonymous inner class implements interface
        Greeting innerClassGreeting = new Greeting() {
            public void perform() {
                System.out.println("Hello World");
            }
        };

        innerClassGreeting.perform();


        //1. lambda looks like greeting anonymous inner classes but they are completely different

        //name of interface should match type
        Greeting lambdaGreeting = () -> System.out.println("Hello World");  // this should match signature of interface "Greeting"

        // how we execute lambda expession
        // calling method on interface
        // this just behaves like instance of class
        //lambda function is just implentation of interface
        // we are implementing an interface by just implementing function and not   implementing class

        lambdaGreeting.perform();  // lambdaGreeting -> is lambda expression of type Greeting interface

        greeter.greet(lambdaGreeting);

        //type inference -> lambda expression maps to type of greeting interface
        greeter.greet(() -> System.out.println("Hello World"));
        greeter.greet(innerClassGreeting);
    }
}





