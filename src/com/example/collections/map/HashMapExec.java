package com.example.collections.map;

import com.example.collections.list.Car;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapExec {

    public static void main(String[] args) {
        Map<Car,Owner> map = new HashMap<Car,Owner>();

        //hashmap doesnot maintain any order
        for (int i = 0; i < 100; i++) {
            map.put(new Car("H - " + i),new Owner());

        }

        //this is 2 step process using keyset
        Iterator<Car> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            Car c = iterator.next();
            System.out.println(c +  " Owner :" +  map.get(c));
        }

        System.out.println("Entry Set ....");

        //entrySet to get values in 1 shot
        Set<Map.Entry<Car,Owner>> mapEntries = map.entrySet();

        Iterator<Map.Entry<Car,Owner>> mapIterator = mapEntries.iterator();
        while(mapIterator.hasNext()){
            Map.Entry<Car,Owner> mapEntry = mapIterator.next();
            System.out.println("Key :" +mapEntry.getKey() + "Value " + mapEntry.getValue());
        }

        System.out.println("Entry Set ..../FOR Loop");

        for(Map.Entry<Car,Owner> entry : mapEntries){
            System.out.println("Key :" +entry.getKey() + "Value " + entry.getValue());

        }


    }
}
