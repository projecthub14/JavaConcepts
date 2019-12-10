package com.example.java8.defaultMethod;

public interface FourWheeler {

    default void print(){
        System.out.println("I am four wheeler");
    }
}
