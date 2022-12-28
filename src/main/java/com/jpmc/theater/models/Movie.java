package com.jpmc.theater.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/*
Movie class with attributes movie title, duration, ticket price, special movie
 */
@JsonIgnoreProperties(value = {
        "specialMovie",
        "runningTime"
})

@Data
public class Movie {
    @JsonUnwrapped
    private String title;
    private String movieDuration;
    private Duration runningTime;
    private double ticketPrice;
    private boolean specialMovie;

    public Movie(String title, Duration runningTime, double ticketPrice, boolean special) {
        this.title = title;
        this.runningTime = runningTime;
        this.movieDuration = movieDuration(runningTime);
        this.ticketPrice = ticketPrice;
        this.specialMovie = special;
    }

    public String movieDuration(Duration duration) {

        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin,
                handlePlural(remainingMin));
    }

    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "s";
        }
    }


}