package com.vaos.store.api;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Tests {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2023, 04, 20);
        LocalDate end = LocalDate.of(2023, 06,27);

        Long daysBetween = ChronoUnit.DAYS.between(start, end);
        System.out.println("Quantos dias: " + daysBetween);
    }
}
