package com.example.concurrency.liveLock;

public class SharedResource {

    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    //since we are changing data so synchronize this
    public synchronized  void setOwner(Worker owner) {
        this.owner = owner;
    }
}
