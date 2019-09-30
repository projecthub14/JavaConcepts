package com.example.collections.equalHashCode;

import java.util.Objects;

public class Student {
    private String registrationNumber;

    public Student(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    //avoid duplicates
//    @Override
    public boolean equals(Object obj) {
        //System.out.println("From Map ");
        if(obj != null && obj instanceof Student) {
            String registrationNumber = ((Student) obj).getRegistrationNumber();
            if (registrationNumber != null && this.registrationNumber.equals(registrationNumber)) {
                return true;
            }
        }

        return false;
    }

    //collections uses strategy to fetch them
    // hashcode shoulde be unique with respect to each other
   // @Override
    public int hashCode() {
      //  System.out.println("From Maop ");

       return registrationNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "registrationNumber='" + registrationNumber + '\'' +
                '}';
    }
}
