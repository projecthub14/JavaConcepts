package com.example.singletom;

//https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/
public class SingletonClass {

//1. create static instance of this object :
    //static SingletonClass singletonClass = new SingletonClass();//eager because even if we are not calling this it will be in memory
    static SingletonClass singletonClass ;
    //so make it lazy
    //2. dont allow user to create class using default construtor

    private SingletonClass(){
        System.out.println("Instance Created ");
    }

    //3. create a static method which returns static instance of this object
    //using synchronized will reduce the performance if method has to do huge computation
    //so use double check locking -> use synchronized blocks
    // we check if obj is null 2 times
    //1st time ->
    //2nd time after synchrnized block we again check if obj is null

    //Enum - above 1.5

    //using inner static class
    public static class BillPughSingleton{
        private static final SingletonClass INSTANCE = new SingletonClass();
    }

    public static SingletonClass getBillInstance(){
        return BillPughSingleton.INSTANCE;
    }


    public static  SingletonClass getInstance(){
        if(singletonClass == null){
            synchronized (SingletonClass.class){

                if(singletonClass == null){
                    singletonClass =  new SingletonClass();
                }


            }

        }

        return singletonClass;
    }
}
