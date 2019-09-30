package com.example.collections.list;

public class Car {

    private String registrationNumber;

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

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
