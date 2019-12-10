package com.example.java8.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TestTemporalAdjusters {

    public static void main(String[] args) {
        LocalDate date1 = LocalDate.now();
        System.out.println("Current date " + date1);

        //get next tuesday
        LocalDate nextTuesday = date1.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println("Next Tuesday " + nextTuesday);

        //get second saturday of next month
        LocalDate firstInYear = LocalDate.of(date1.getYear(),date1.getMonth(),1);

        LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println("Second Saturday on : " + secondSaturday);

    }
}
