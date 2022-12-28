package com.jpmc.theater.impl;


import com.jpmc.theater.models.Showing;

/*
Implementation of Discount interface to calculate movie discount fee based on show sequence
 */
public class ShowSequenceDiscount implements Discount {


    @Override
    public double movieDiscount(Showing showing) {

        int showSequence = showing.getSequenceOfTheDay();

        if (showSequence == 1) {
            return 3;
        } else if (showSequence == 2) {
            return 2;
        } else if (showSequence == 7) {
            return 1;
        } else if (showSequence == 8) {
            return 1.75;
        }
        return 0;

    }
}
