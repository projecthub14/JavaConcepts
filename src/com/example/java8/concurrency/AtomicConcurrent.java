package com.example.java8.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicConcurrent {

    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        IntStream.range(0,1000).forEach( i -> executorService.submit(atomicInt::incrementAndGet));

        executorService.shutdown();

        System.out.println(atomicInt.get());


        ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("foo","bar");
        map.put("han","solo");
        map.put("r2","d2");
        map.put("c3","p0");

        map.forEach((key,value) -> System.out.println(key + "," + value));

        String value = map.putIfAbsent("c3", "p1");
        System.out.println(value);    // p0

        String value1 = map.getOrDefault("hi", "there");
        System.out.println(value1);    // there

        map.replaceAll((key, value2) -> "r2".equals(key) ? "d3" : value2);
        System.out.println(map.get("r2"));    // d3

        map.compute("foo", (key, value4) -> value4 + value4);
        System.out.println(map.get("foo"));   // barbar

        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));   // boo was foo
    }
}
