package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.models.Customer;
import com.jpmc.theater.models.Movie;
import com.jpmc.theater.models.Showing;
import com.jpmc.theater.service.Reservation;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/*
A theater application which takes user input to print the show schedule in text, json format and reserves the ticket taking customer details & show sequence
 */

public class TheaterApp {

    private List<Showing> schedule;
    private Reservation reserve;


    public static void main(String[] args) {

        int option = 1;
        TheaterApp theater = new TheaterApp();

        do {
            try {
                theater.menu();
                theater.movieTicketsAndReservations(theater);
            } catch (InputMismatchException ex) {
                System.out.println(ex.getMessage());

            }

        } while (option != 0);
        theater.movieTicketsAndReservations(theater);
        System.exit(0);
    }

    private void menu() {
        System.out.println("-----------------------------------------");
        System.out.println("Please choose from the following options:");
        System.out.println("Exit                               ---> 0");
        System.out.println("Print Schedule in Text Format      ---> 1");
        System.out.println("Print Schedule in JSON Format      ---> 2");
        System.out.println("Reserve Movie                      ---> 3");

    }


    public TheaterApp() {

        try {

            LocalDate currentDate = LocalDate.now();

            Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, true);
            Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, false);
            Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, false);

            schedule = Arrays.asList(new Showing(turningRed, 1, LocalDateTime.of(currentDate, LocalTime.of(9, 0))),
                    new Showing(theBatMan, 2, LocalDateTime.of(currentDate, LocalTime.of(11, 0))),
                    new Showing(spiderMan, 3, LocalDateTime.of(currentDate, LocalTime.of(12, 50))),
                    new Showing(turningRed, 4, LocalDateTime.of(currentDate, LocalTime.of(14, 30))),
                    new Showing(spiderMan, 5, LocalDateTime.of(currentDate, LocalTime.of(16, 10))),
                    new Showing(theBatMan, 6, LocalDateTime.of(currentDate, LocalTime.of(17, 50))),
                    new Showing(turningRed, 7, LocalDateTime.of(currentDate, LocalTime.of(19, 30))),
                    new Showing(spiderMan, 8, LocalDateTime.of(currentDate, LocalTime.of(21, 10))),
                    new Showing(theBatMan, 9, LocalDateTime.of(currentDate, LocalTime.of(23, 0))));


        } catch (Exception ex) {
            throw ex;
        }

    }


    public void movieTicketsAndReservations(TheaterApp theater) {
        boolean reserveComplete = false;
        try {
            Scanner scanner = new Scanner(System.in);
            int userInput = scanner.nextInt();
            switch (userInput) {
                case 0: {
                    System.out.println("Thank you for your time. Exiting the program!!");
                    System.exit(0);
                }
                case 1:
                    theater.printSchedule();
                    break;
                case 2:
                    theater.printJSONSchedule();
                    break;
                case 3: {
                    System.out.println("Please provide your name, show sequence & number of tickets required with comma separation");
                    String customerDetails = scanner.next();
                    reserveComplete = theater.reserveTicket(customerDetails);
                    break;
                }
                default:
                    System.out.println("Please provide the correct option");


            }
        } catch (InputMismatchException ex) {
            System.out.println("Not a valid input. Please provide correct option");
        }

    }


    /*
      Print movie schedule in JSON format using jackson object mapper
     */
    public void printJSONSchedule() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");


        schedule.stream().forEach(s -> {
            try {

                String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(s);
                System.out.println(json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });


    }

    /*
      Print movie schedule in simple text format
     */
    public void printSchedule() {

        try {

            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%5s %15s %25s %25s %20s %20s", "SHOW SEQUENCE", "SHOW TIME", "MOVIE", "SHOW DURATION", " TICKET PRICE", "DISCOUNTED PRICE");
            System.out.println();
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

            schedule.stream().forEach(s -> {
                System.out.format("%5s %25s %25s %25s %15s %15s", s.getSequenceOfTheDay(), s.getShowStartTime(), s.getMovie().getTitle(), s.getMovie().getMovieDuration(), s.getMovie().getTicketPrice(), s.getDiscountedMovieFee());
                System.out.println();
            });
        } catch (UnknownFormatConversionException fx) {
            System.out.println("Incorrect Spacing" + fx.toString());
        } catch (IllegalFormatConversionException ex) {
            System.out.println("Incorrect Formatting" + ex.toString());
        }


    }

    /*
    Method takes customer details, show sequence & ticket count as input, calculates the total fee for head count and displays reservation information
     */
    public boolean reserveTicket(String customerDetails) {

        try {
            String[] reserveInputs = customerDetails.split(",");
            boolean isValid = validateInputs(reserveInputs);
            if (isValid) {
                Customer customer = new Customer(1, reserveInputs[0]);
                Showing showing = schedule.get(Integer.parseInt(reserveInputs[1]) - 1);
                reserve = new Reservation(customer, showing, Integer.parseInt(reserveInputs[2]));
                System.out.println("-----------------------------------RESERVATION CONFIRMATION----------------------------------------------------------------------------");
                System.out.printf("%5s %15s %25s %25s %20s %20s", "CUSTOMER NAME", "MOVIE", "SHOW TIME", "TICKET PRICE", "TICKET COUNT", "TOTAL AMOUNT");
                System.out.println();
                System.out.format("%5s %25s %25s %20s %15s %20s", customer.getName(), showing.getMovie().getTitle(), showing.getShowStartTime(), showing.getDiscountedMovieFee(), reserve.getAudienceCount(), reserve.totalFee());
                System.out.println();
                System.out.println("---------------------Thank you for your purchase. We look forward to seeing you at the theatre-----------------------------------------");
                return true;
            }

        } catch (UnknownFormatConversionException fx) {
            System.out.println("Incorrect Spacing" + fx.toString());
        } catch (IllegalFormatConversionException ex) {
            System.out.println("Incorrect Formatting" + ex.toString());
        }

        return false;
    }

    /*
    This method validates the input provided by the user to make a reservation
     */
    public boolean validateInputs(String[] customerDetails) {

        if (customerDetails != null && !customerDetails.equals("") && customerDetails.length == 3) {
            try {
                int sequence = Integer.parseInt(customerDetails[1]);
                int ticketCount = Integer.parseInt(customerDetails[2]);
                if (sequence <= 0 || sequence > schedule.size()) {
                    System.out.println("Please provide the correct Show sequence to reserve a ticket!!");
                    return false;
                }
                return true;

            } catch (NumberFormatException num) {
                System.out.println("Please provide valid number for Show Sequence or Ticket Count !!");
                return false;
            }
        } else {
            System.out.println("Please provide complete information to make a ticket reservation!!");
            return false;
        }


    }
}
