package com.jpmc.theater.impl;

import com.jpmc.theater.models.Showing;

/*
Implementation of Discount interface to calculate discount fee based on special hours - 25% discount applied for shows from 11AM to 4 PM
 */
public class SpecialHourDiscount implements Discount {


    @Override
    public double movieDiscount(Showing showing) {
        int hours = showing.getShowStartTime().getHour();

        if (hours >= 11 && hours <= 16) {
            return showing.getMovie().getTicketPrice() * 0.25;
        }
        return 0;
    }
}
