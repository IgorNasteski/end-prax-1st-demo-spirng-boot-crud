package com.example.demo.service;

import com.example.demo.dao.OrdersRepository;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.DateTimeComparator;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


//@Service
public class Methods {

    @Autowired
    private OrdersRepository ordersRepository;


    LocalDate from = LocalDate.parse("2015-05-01");
    LocalDate to = LocalDate.parse("2015-05-07");

    long days = DAYS.between(from, to);    // 6 days
    long weeks = ChronoUnit.WEEKS.between(from, to);  // 0 weeks

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }



    long noOfDaysBetween = DAYS.between(from, to);

}
