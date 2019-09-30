package com.example.concurrency.deadlock;

public class SayHelloMain {
    public static void main(String[] args) {

        PolitePerson sowmya = new PolitePerson("sowmya");
        PolitePerson sharvi = new PolitePerson("sharvi");

        new Thread(new Runnable(){

            @Override
            public void run() {
                sowmya.sayHello(sharvi);
            }
        }).start();


        new Thread(new Runnable(){

            @Override
            public void run() {
                sharvi.sayHello(sowmya);
            }
        }).start();

    }
// Thread1 acquires the lock on sowmya object and enters the sayHello() method. It prints to consoles and suspends
// Thread2 acquirs the lock of shaarvi object and enters the sayHello() method . It print to console and suspends
// Thread1 runs again and wants to sayHelloBack to shaarvi obect. It tries to call sayHelloBack() method using shaarvi object that was passed to sayHello()
    //but thread2 is holding shaarvi object , so Thread1 suspends
// Thread2 runs again and wants to sayHelloBack to sowmya obect. It tries to call sayHelloBack() method using sowmya object that was passed to sayHello()
    //but thread1 is holding lock for sowmya object so it suspends

    //deadlock occures because of order in which threads are trying to acquire locks


    static class PolitePerson {
        private final String name;

        public PolitePerson(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

        public synchronized void sayHello(PolitePerson person){

            System.out.format("%s : %s" + "has said hello to me!\n " , this.name , person.getName());
            person.sayHelloBack(this);
        }

        public synchronized void sayHelloBack(PolitePerson person){
            System.out.format("%s : %s" + "has said hello back to me\n" , this.name , person.getName());

        }
    }
}


