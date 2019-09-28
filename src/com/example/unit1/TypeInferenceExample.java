package com.example.unit1;

//StringLengthLambda myLambda = (String s) -> s.length();

//1. We are giving type of argument in signature so remove String
//2. if we have only one parameter then remove paranthesis

public class TypeInferenceExample {

    public static void main(String[] args) {
        StringLengthLambda myLambda =  s -> s.length();

        System.out.println(myLambda.getLength("sd"));
        printLambda(s -> s.length()); // this is type inference

    }

    public static void printLambda(StringLengthLambda len){
        System.out.println(len.getLength("Sowmya"));
    }

    //We can look into interface for :
    // type of argument
    // return type
    // method to call lambda expression
    interface StringLengthLambda {
        int getLength(String s);
    }
}
