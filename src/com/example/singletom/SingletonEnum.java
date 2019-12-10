package com.example.singletom;

public class SingletonEnum {

    public static void main(String[] args) {

        SingletonEnumClass enumObj = SingletonEnumClass.INSTANCE;
        enumObj.i = 5;
        enumObj.show();

        SingletonEnumClass enumObj1 = SingletonEnumClass.INSTANCE;
        enumObj.i = 6;
        enumObj.show();
    }
}

enum SingletonEnumClass{
    INSTANCE;
    int i;
    public void show(){
        System.out.println(i);
    }
}