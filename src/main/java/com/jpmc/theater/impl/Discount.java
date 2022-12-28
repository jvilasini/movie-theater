package com.jpmc.theater.impl;

import com.jpmc.theater.models.Showing;

/*
Interface to calculate discount fee based on various criteria (like special movie, hour, sequence)
 */

public interface Discount {

    public double movieDiscount(Showing showing);

}
