package com.example.concurrency.challenge;

//there is thread interference when two threads are trying access
//same bankaccount instance

//make the backaccount class threadsafe using synchronize keyword :
public class BankAccount {

    private  double balance;
    private String accountNumber;

    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
    }


    //put synchronize where we are updating value
    public  void deposit(double amount){
        synchronized (this){
            balance +=amount;
        }

    }

    public synchronized void withdraw(double amount){
        synchronized (this){
            balance -=amount;
        }

    }

    public static void main(String[] args) {
        BankAccount account1 = new BankAccount(1000.00 , "12345-678");
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


