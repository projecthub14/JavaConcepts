package com.example.java8.datetime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TestChronoUnits {


    public static void main(String[] args) {


        LocalDate today = LocalDate.now();
        System.out.println("Current date "+ today);

        //add 1 week to current date
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Next week " + nextWeek);

        //add 1 month to current date
        LocalDate nextMonth = today.plus(1,ChronoUnit.MONTHS);
        System.out.println("Next month " + nextMonth);

        //add 1 year to current date
        LocalDate nextYear = today.plus(1,ChronoUnit.YEARS);
        System.out.println("Next Year "  + nextYear);

        //add 10 years to current date
        LocalDate nextDecade = today.plus(1,ChronoUnit.DECADES);
        System.out.println("Date after 10 years "+ nextDecade);
    }
}
