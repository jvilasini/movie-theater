package com.jpmc.theater;

import com.jpmc.theater.models.Movie;
import com.jpmc.theater.models.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTest {

    LocalDate currentDate = LocalDate.now();

    /*
    No discounts applied for the show
     */
    @Test
    void NoMovieDiscount() {

        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);
        Showing showing = new Showing(theBatMan, 9, LocalDateTime.of(currentDate, LocalTime.of(10, 0)));
        assertEquals(9, showing.getDiscountedMovieFee());

    }


    /*
    Discount of 20% is applied as it is Special Movie
     */
    @Test
    public void SpecialMovieDiscountTest() {

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 20, true);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(currentDate, LocalTime.of(9, 30)));
        assertEquals(16, showing.getDiscountedMovieFee());

    }

    /*
    Discount of $3 is applied as it is First Show sequence
     */
    @Test
    public void FirstSequenceDiscountTest() {

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
        Showing showing = new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(9, 30)));
        assertEquals(8, showing.getDiscountedMovieFee());

    }

    /*
   Discount of $2 is applied as it is Second Show sequence
    */
    @Test
    public void SecondSequenceDiscountTest() {

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
        Showing showing = new Showing(turningRed, 2, LocalDateTime.of(currentDate, LocalTime.of(9, 30)));
        assertEquals(9, showing.getDiscountedMovieFee());

    }

    /*
    Discount of $1 is applied as it is Seventh Show sequence
     */
    @Test
    public void SeventhSequenceDiscountTest() {

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
        Showing showing = new Showing(turningRed, 7, LocalDateTime.of(currentDate, LocalTime.of(9, 30)));
        assertEquals(10, showing.getDiscountedMovieFee());

    }

     /*
     Special Hour Discount $2.5 is applied as the show hours falls between 11 AM and 4 PM
     */

    @Test
    public void SpecialHourDiscountTest() {

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 10, false);
        Showing showing = new Showing(turningRed, 2, LocalDateTime.of(currentDate, LocalTime.of(14, 30)));
        assertEquals(7.5, showing.getDiscountedMovieFee());

    }

    /*
    Special Hour Discount $2.5 and First Show discount $3 both applies for the below test case but the highest discount is first show discount of $3 is applied
     */
    @Test
    public void HighestDiscountTest() {

        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 10, false);
        Showing showing = new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(14, 30)));
        assertEquals(7, showing.getDiscountedMovieFee());

    }

    /*
Special Hour Discount $2 and Special movie $2.5 both applies for the below test case but the highest discount is SpecialMovie of $2.5 is applied
 */
    @Test
    public void SpecialMovieOrHourDiscountTest() {

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 10, true);
        Showing showing = new Showing(spiderMan, 2, LocalDateTime.of(currentDate, LocalTime.of(13, 30)));
        assertEquals(7.5, showing.getDiscountedMovieFee());

    }


}
