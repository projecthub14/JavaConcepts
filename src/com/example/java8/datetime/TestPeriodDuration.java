package com.example.java8.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class TestPeriodDuration {




    public static void main(String[] args) {

        LocalDate date1 = LocalDate.now();
        System.out.println("Current Date: " + date1);

        //Add 1 month to current date
        LocalDate date2 = date1.plus(1, ChronoUnit.MONTHS);
        System.out.println("After 1 month " + date2);

        Period period = Period.between(date2,date1);
        System.out.println("Period " + period);



        ///////////

        LocalTime time = LocalTime.now();
        Duration twoHours = Duration.ofHours(2);

        LocalTime time1 = time.plus(twoHours);
        Duration duration = Duration.between(time1,time);

        System.out.println("Duration is " + duration);
    }
}
