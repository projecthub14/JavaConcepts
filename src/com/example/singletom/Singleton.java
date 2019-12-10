package com.example.singletom;

public class Singleton {

    public static void main(String[] args) {




        new Thread(() -> {
            SingletonClass obj1 = SingletonClass.getInstance();
        }).start();

        new Thread(() -> {
            SingletonClass obj2 = SingletonClass.getInstance();
        }).start();
    }
}


