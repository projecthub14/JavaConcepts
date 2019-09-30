package com.example.concurrency.deadlock;


//Only one thread can run synchronized block

//If Thread1 calls Data.updateData() while another thread Thread2 calls
//Display.updateDisplay foolowing happens based on timing :

//1. Thread1 enter Data.updateData() and writes to console and suspends
//2. Thread2 enter Display.updateDisplay and writes to console and suspends

//3. Thread1 runs and tries to call display.dataChanged(), byt thread2 is still
//running Display.updateDisplay, so its holding Display lock object so Thread1 blocks

//4. Thread2 wakes up and tries to run Data.getDatat() but Thread1 is still
//running data.updateData() , so Thread2 blocks

//Deadlock occurs - two threads trying to get same two locks in different order.
//Thread1 wants Data lock whereas thread2 want display lock

//solution
public class DeadLockllustration {

    public static class Data {

        private Display display;

        public void setDisplay(Display display){
            this.display = display;
        }

        public void updateData(){
            System.out.println("Updating data .... ");
            display.dataChanged();
        }

        public Object getData(){
            return new Object();
        }

    }


    public static class Display{

        private Data data;

        public void setData(Data data){
            this.data = data;
        }

        public void dataChanged(){
            System.out.println("I'm executing code with changed data");
        }

        public void updateDisplay(){
            System.out.println("Updating display ....");
            Object o = data.getData();
        }

    }
}



