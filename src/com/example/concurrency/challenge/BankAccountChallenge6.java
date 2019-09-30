package com.example.concurrency.challenge;

//there is thread interference when two threads are trying access
//same bankaccount instance

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//make the backaccount class threadsafe using reentrant lock :
public class BankAccountChallenge6 {

    //use only one lock where both threads compete for same lock
    private Lock lock;
    private  double balance;
    private String accountNumber;

    public BankAccountChallenge6(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        //initialise in constructor
        this.lock = new ReentrantLock();
    }


    //put synchronize where we are updating value
    //add statuc variable to both methods
    //if thread gets lock then set status to true
    public  void deposit(double amount){
        //ensure lock will be released
        boolean status = false;

        //tryLock shows interuppted Exception
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance += amount;
                    status = true;
                }
                finally {
                    lock.unlock();
                }
            }
            else{
                System.out.println("Could not get lock");
            }

        } catch (InterruptedException e) {

        }
        System.out.println("Transaction status = "  +Thread.currentThread().getName() + " " +status);
    }

    public  void withdraw(double amount) {

        //since status variable is local variable its already thread safe

        boolean status = false;
        try {
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try {
                    balance -= amount;
                    status = true;
                }
                finally {
                    lock.unlock();
                }
            }
            else{
                System.out.println("Could not get lock");
            }

        } catch (InterruptedException e) {

        }
        System.out.println("Transaction status " +Thread.currentThread().getName() + " " + status);
    }

    //no need of synchronize since we are just reading values and
    //there will be no thread interference
    public String getAccountNumber(){
            return accountNumber;
    }

    public void printAccountNumber(){
            System.out.println("Account Number is " + accountNumber);
    }


    public static void main(String[] args) {

        BankAccountChallenge6 account1 = new BankAccountChallenge6(1000.00 , "12345-678");
        new Thread(new Runnable() {
            @Override
            public void run() {
               account1.deposit(300.00);
                System.out.println("Thread1 : Balance after deposit " + account1.balance);
               account1.withdraw(50.00);
                System.out.println("Thread1 : Balance after withdraw " + account1.balance);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                account1.deposit(203.75);
                System.out.println("Thread2 : Balance after deposit " + account1.balance);
                account1.withdraw(100.00);
                System.out.println("Thread2 : Balance after withdraw " + account1.balance);
            }
        }).start();
    }
}


