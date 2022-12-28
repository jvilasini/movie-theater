package com.jpmc.theater.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpmc.theater.impl.Discount;
import com.jpmc.theater.impl.ShowSequenceDiscount;
import com.jpmc.theater.impl.SpecialHourDiscount;
import com.jpmc.theater.impl.SpecialMovieDiscount;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Showing class with attributes movie , sequence of the day, show start time and calculation of discounted movie fee
 */

@Data
public class Showing {

    private Movie movie;
    private int sequenceOfTheDay;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime showStartTime;
    private double discountedMovieFee;


    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.discountedMovieFee = getDiscountedMovieFee();

    }


   /*

    */
    public double getDiscountedMovieFee() {
        double discount = 0;
        List<Discount> discounts = Arrays.asList(new ShowSequenceDiscount(), new SpecialMovieDiscount(), new SpecialHourDiscount());
        List<Double> discountedFees = discounts.stream().map(d -> d.movieDiscount(this)).collect(Collectors.toList());
        discount = discountedFees.stream().max(Comparator.comparing(String::valueOf)).get();
        return (getMovie().getTicketPrice() - discount);

    }
}
