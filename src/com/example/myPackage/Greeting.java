package com.example.myPackage;

// type of lambda variable
public interface Greeting {

    //use only one method in interface
    //otherwise compiler gives error in lambda expression compaining which signature to match
    public void perform(); // ------ lambda signature ----------

}
