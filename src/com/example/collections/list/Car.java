package com.example.collections.list;

import java.util.Comparator;

public class Car implements Comparable<Car> {

    private String registrationNumber;
    private int price;

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public int hashCode() {
        //string implementation of hashcode
        return this.registrationNumber.hashCode();
    }



    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj instanceof Car){
            String regNumber = ((Car)obj).getRegistrationNumber();
            //string implementation of equals
            if(regNumber != null && regNumber.equals(this.registrationNumber)){
                return true;
            }
        }
        return false;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                '}';
    }

//    object1.compareTo(object2)
//>>>> if it returns -1 -> ordering is first object2 then object1
//>>>> if it returns 1 -> ordering is first object 1 then object2
//>>>> if it 0 -> stand at same level

    @Override
    public int compareTo(Car o) {
        if(this.getPrice() > o.getPrice()){
            return 1;
        }
        else if(this.getPrice() < o.getPrice()){
            return -1;
        }

        return 0;
    }
}
