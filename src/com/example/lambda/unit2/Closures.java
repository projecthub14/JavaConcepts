package com.example.lambda.unit2;

public class Closures {

    public static void main(String[] args) {

        //in java 7 we had to effectively mention variable as final
        //but now we dont have to mention its by default final in java 8
        //if we try to change the variable value in innerclass it gives error
        int a = 10;
        int b = 20; // -----this is effectively final ----
//        doProcess(a,new Process(){
//
//            @Override
//            public void process(int i) {
//                //b = 30; gives error as its effectively final
//                System.out.println(i + b); // we can read variable in inner class
//            }
//        });
        doProcess(a, i -> System.out.println(i+b)); // ------b is coming from closures // in same scope ---
    }



    public static void doProcess(int i,Process p){ // p contains value of b
        p.process(i);
    }
}

interface Process{
    void process(int i);
}