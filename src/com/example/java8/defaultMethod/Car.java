package com.example.java8.defaultMethod;

public class Car implements Vehicle,FourWheeler {

    public void  print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("I am car");
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.print();
    }


}
