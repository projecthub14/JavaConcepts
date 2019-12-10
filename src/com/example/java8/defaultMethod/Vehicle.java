package com.example.java8.defaultMethod;

public interface Vehicle {

    default void print(){
        System.out.println("I am Vehicle");
    }

    static void blowHorn() {
        System.out.println("Blowing horn!!!");
    }
}
