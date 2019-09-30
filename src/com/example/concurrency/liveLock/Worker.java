package com.example.concurrency.liveLock;

public class Worker {

    private String name;
    private boolean active;

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public synchronized  void work(SharedResource sharedResource , Worker otherWorker){

        //only time that this thread will complete loop iteration is that
        //when it owns shared resource and other thread isnt active
        while(active){
            //if worker doesnt own the resource then wait for 10 seconds and check again
            if(sharedResource.getOwner() != this){
                try{
                    wait(10);
                }
                catch (InterruptedException ex){

                }
                continue;
            }
            //check if otherThread is active
            if(otherWorker.isActive()){
                //then politely give the shared resource to other worker
                System.out.println(getName() + " give the resource to worker" + otherWorker.getName());
                sharedResource.setOwner(otherWorker);

                //then return back to beginning of loop
                continue;
            }

            System.out.println(getName() + ": working on common resource ");
            active = false;
            sharedResource.setOwner(otherWorker);
        }
    }
}
