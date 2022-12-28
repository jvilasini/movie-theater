package com.jpmc.theater;

import com.jpmc.theater.models.Customer;
import com.jpmc.theater.models.Movie;
import com.jpmc.theater.models.Showing;
import com.jpmc.theater.service.Reservation;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReservationTest {

    LocalDate currentDate = LocalDate.now();

    /*
    Discount of 20% is applied as it is Special Movie and total fee of $32 is calculated for 2 people
    */
    @Test
    public void reserveSpecialMovieDiscountTest() {
        LocalDate currentDate = LocalDate.now();

        Customer customer = new Customer(1, "Vilasini");
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, true);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(currentDate, LocalTime.of(9, 30)));

        Reservation reserve = new Reservation(customer, showing, 2);
        assertTrue(reserve.totalFee() == 32);


    }

    /*
     Special Hour Discount $2.5 and First Show discount $3 both applies for the below test case but the highest discount is first show discount of $3 is applied
     and total fee for 3 people are collected as $21
      */
    @Test
    public void reserveWithHighestDiscountTest() {
        LocalDate currentDate = LocalDate.now();

        Customer customer = new Customer(1, "Vilasini");
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 10, false);

        Showing showing = new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(14, 30)));

        Reservation reserve = new Reservation(customer, showing, 3);
        assertTrue(reserve.totalFee() == 21);


    }


}


