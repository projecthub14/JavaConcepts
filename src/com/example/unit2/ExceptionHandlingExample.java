package com.example.unit2;


import java.util.function.BiConsumer;

public class ExceptionHandlingExample {

    public static void main(String[] args) {
        int[] someNumbers = {1,2,3,4};
        int key = 0;


        //externalize exception handling in seperate funtion
        // wrap below lambda with another lambda
        //another lambda has the try catch
        process(someNumbers,key, wrapperLambda((v,k) -> System.out.println(v / k)) ); //(v,k) -> sout(v/k);
                //putting try here destroys whole purpose of single line lambda
//           try{

//           }
//           catch (ArithmeticException exc) {
//               System.out.println("An Arithmetic exception happened");
//           }




    }

    //another wrapper lambda
    private static BiConsumer<Integer, Integer >  wrapperLambda(BiConsumer<Integer, Integer > biConsumer){
       return (v,k) -> {
          try{
              biConsumer.accept(v, k); // returns (v,k) -> sout(v/k);
          }
          catch (ArithmeticException exception){
              System.out.println("Exception caught in wrapper lambda");
          }

        };



    }

    //(v,k) -> sout(v/k);
    private static void process(int[] someNumbers, int key, BiConsumer<Integer, Integer > biConsumer) {
        for (int i : someNumbers){

                biConsumer.accept(i , key);

        }
    }
}
