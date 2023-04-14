package com.vaos.store.api;

import java.util.Calendar;
import java.util.Date;

public class Tests {

    public static void main(String[] args) {
        Calendar calendar  = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        System.out.println(calendar.getTime());
        calendar.add(Calendar.MINUTE, 10);
        System.out.println(new Date(calendar.getTime().getTime()));
    }
}
