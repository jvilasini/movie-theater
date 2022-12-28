package com.jpmc.theater.impl;

import com.jpmc.theater.models.Movie;
import com.jpmc.theater.models.Showing;

/*
Implementation of Discount interface to calculate 20% discount fee for special movie
 */

public class SpecialMovieDiscount implements Discount {

    @Override
    public double movieDiscount(Showing showing) {

        Movie movie = showing.getMovie();

        if (movie.isSpecialMovie()) {
            return movie.getTicketPrice() * 0.2;
        }
        return 0;
    }


}
