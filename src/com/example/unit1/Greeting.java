package com.example.unit1;

@FunctionalInterface // this is meant to be interface for lambda
// type of lambda variable
public interface Greeting {

    //use only one method in interface
    //otherwise compiler gives error in lambda expression compaining which signature to match
    public void perform(); // ------ lambda signature ----------

    //if we tru to add another method then we see error as lambda doesnot
    //support two methods in interface

    //public void addmethod();

}
