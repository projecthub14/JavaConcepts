package com.example.lambda.unit2;

public class ThisReferenceExample {

    public void doProcess(int i , Process p){
        p.process(i);
    }

    public void execute(){
        System.out.println(this); // refers to ThisReferenceExample
        this.doProcess(10, i -> {
            System.out.println("Value of i is " + i);


            // instance of lambda doesnot touch / unmodify / not overwriting this -> this is by design
            //value of this is same as value of this outside lambda expression

             System.out.println(this); // refers to ThisReferenceExample
        });



    }

    public String toString() {
        return "This is ThisReferenceExample class";
    }


    public static void main(String[] args) {
        ThisReferenceExample thisReferenceExample = new ThisReferenceExample();
        //System.out.println(this); cannot use this here in static context

//        thisReferenceExample.doProcess(10, new Process() {
//            @Override
//            public void process(int i) {
//                System.out.println("Value of i is " + i);

//                System.out.println(this); // this is anonymous inner/ inline class
//            }
//
//            @Override
//            public String toString() {
//                return "This is anonymous inner class";
//            }
//        });
        thisReferenceExample.execute();

//        thisReferenceExample.doProcess(10, i -> {
//            System.out.println("Value of i is " + i);
//
//            // instance of lambda doesnot touch / unmodify / not overwriting this -> this is by design
//            //value of this is same as value of this outside lambda expression
//
//           // System.out.println(this); // cannot use this as its in static context
//        });


    }


}
