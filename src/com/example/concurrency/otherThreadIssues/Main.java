package com.example.concurrency.otherThreadIssues;


//1. Atomic action : thread can be suspended when its in middle of doing something

//eg : if thread calls sout method , it can be suspended in middle of executing method, it may have evaluated the arguments being passed , but its suspended
//before it can print the result
//or it may be partway through evaluating arguments when its suspended
// Essentially sout isnt atomic action

//An atomic action cant be suspended in the middle of being executed. It can either be completed or it doesnt happen at all
//In Java following operations are atomic :

//1. Reading and writing object reference variables :
// myObject1 = myObject2 would be atomic . A thread can't suspend in middle of executing this statement
// 2. reading writing primitive variables execept those of double / long.JVM requires two operations to read and write long / doubles so thread can suspend between each operations.
//3. reading writing all variables declared volatile.

//volatile variable : -> we may think that there will be no thread interference with atomic action and we dont need to synchronize them , but its not true

//becaus of way java manages memory , it is possible to get memory consistency error when multiple threads  can read and write the same variable
//each thread contains CPU cache that contains value that are  in main memory . since its faster read from cache , this can improve performance but nowadays most computers
//have more than one CPU

//when running application each thread can be running on different CPU , each CPU has its own cache , its possible that values of cache to become out of sync with each other
//and with value in main memory - memory consistency error

//------------
//suppose there are two threads that use same int counter . Thread1 reads and writes the counter. Thread2 only reads the counter.
// reading and writing to an int is atomic action. -> wont suspend but lets suppose thread1 is running in CPU1 and thread2 is running in CPU2 , because of caching following happen :

//1. value of counter is 0 in main memory
//2. Thread1 reads value 0 from main memory.
//3. Thread1 adds 1 to value
//4. Thread1 writes value 1 to its CPU cache
//5. Thread2 reads the value of counter from main memory and gets 0 rather than latest value 1


//Volatile variable come in -> if we use non volatile variable the JVM doesnt guarantee , when it will write the updated value back to main memory .
//..But if we use volatile variable then JVM writes update value back to main memory immediately after a thread updates the value in its cache.
// it also guarantees that everytime it reads from volatile variable it reads latest value

//public volatile int counter; --> still with volatic operations will be volatile but there will be
//memory consistency error

//instead of using int counter we can use AtomicInteger object , When using one of Atomic types , we dont have to worry about thread interference
//classes in java.util.concurrent.atomic package "support lock free  thread safe programming on sinle variable"


//use AtomicInteger -> AtomicInteger counter = new AtomicInteger(0);
//we dont have to syncronize any of increment or decrement operation
//to increment -> counter.incrementAndGet();
//to decrement -> counter.decrementAndGet();
//to get value -> counter.get()

//compareAndSet(expectedValue , newValue ) -> if current value is not equal to expected value then returns false
//and doesnt set new value -> this is useful when thread knows it might be suspended between getting value and updating it



//for more info read Master class video




public class Main {
}
