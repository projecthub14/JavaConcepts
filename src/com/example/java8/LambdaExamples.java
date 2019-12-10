package com.example.java8;

public class LambdaExamples {

    public static void main(String[] args) {

        //inside lambda expression we can refer to any final variable


        //with type declaration
        MathOperation addition = new MathOperation() {
            @Override
            public int operation(int a, int b) {
                return a+b;
            }
        };

        //no type declaration
        MathOperation subtraction = (c , d) -> c -d;

        //with braces and return statement
        MathOperation multiplication = (c , d) -> { return c * d ; };

        //without return statement and curly braces
        MathOperation division = (c , d) ->  c / d ;

        System.out.println(division.operation(8,4));

        System.out.println(operate(2,3,addition));
        System.out.println(operate(6,3,subtraction));

        //without paranthesis
        GreetingService greetService1 = message -> System.out.println("Hello" + message);

        //with paranthesis
        GreetingService greetService2 = (message) -> System.out.println("Hello" + message);

        greetService1.sayHello("hi");
    }

    interface MathOperation{
        int operation(int a , int b);
    }

    interface GreetingService{
        void sayHello(String message);
    }

    private static int operate(int a , int b , MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }
}
