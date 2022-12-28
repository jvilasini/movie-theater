package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

public class TheaterAppTest {

    LocalDate currentDate = LocalDate.now();
    TheaterApp theater = new TheaterApp();

    @Test
    public void validateInputsTest() {

        String[] input = {"Vila", "1", "1"};
        assertEquals(true, theater.validateInputs(input));
    }

    @Test
    public void incompleteInputTest() {

        String[] input = {"Vila"};
        assertEquals(false, theater.validateInputs(input));
    }

    @Test
    public void incorrectSequenceInputTest() {

        String[] input = {"Vila", "0", "1"};
        assertEquals(false, theater.validateInputs(input));
    }

    @Test
    public void reserveTicketTest() {
        String input = "Vilasini,1,1";
        assertEquals(true, theater.reserveTicket(input));
    }

    @Test
    public void noReserveTicketTest() {
        String input = "Vilasini";
        assertEquals(false, theater.reserveTicket(input));
    }
}
