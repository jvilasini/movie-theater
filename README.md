# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## Code Enhancements
1) Main method invokes the Theater constructor and displays the menu to make a choice
2) Movie object constructor converts the movie duration in readable format while instantiation
3) Showing object calculates discounted fee while instantiation. 
4) SpecialMovie discount, SpecialHour Discount & Sequence discount are added to the list and looped in using lambda stream to find the highest discount fee
5) Upon pressing 1 displays the movie show schedule in text format
6) Upon pressing 2 displays th movie show schedule in JSON format
7) Upon pressing 3 prompts to make a reservation by entering Customer name, show sequence and ticket count
8) Reservation method validates the input and gives message when incorrect input is provided
9) If correct inputs are provided, the reservation class is instantiated and shows the reservation details as shown below 
10) JUNIT test cases implemented to test the discounted fee, total fee calculations and reservations.

Menu Display:
```
Please choose from the following options:
Exit                               ---> 0
Print Schedule in Text Format      ---> 1
Print Schedule in JSON Format      ---> 2
Reserve Movie                      ---> 3
```
Input: 1

Output:
```-------------------------------------------------------------------------------------------------------------------------
SHOW SEQUENCE       SHOW TIME                     MOVIE             SHOW DURATION         TICKET PRICE     DISCOUNTED PRICE
-------------------------------------------------------------------------------------------------------------------------
    1          2022-12-28T09:00               Turning Red         1 hour 25 minutes            11.0             8.0
    2          2022-12-28T11:00                The Batman         1 hour 35 minutes             9.0            6.75
    3          2022-12-28T12:50   Spider-Man: No Way Home         1 hour 30 minutes            12.5           9.375
    4          2022-12-28T14:30               Turning Red         1 hour 25 minutes            11.0            8.25
    5          2022-12-28T16:10   Spider-Man: No Way Home         1 hour 30 minutes            12.5           9.375
    6          2022-12-28T17:50                The Batman         1 hour 35 minutes             9.0             9.0
    7          2022-12-28T19:30               Turning Red         1 hour 25 minutes            11.0            10.0
    8          2022-12-28T21:10   Spider-Man: No Way Home         1 hour 30 minutes            12.5            10.0
    9          2022-12-28T23:00                The Batman         1 hour 35 minutes             9.0             9.0
```

Input: 2


OUTPUT:
```
{
  "movie" : {
    "title" : "Turning Red",
    "movieDuration" : "1 hour 25 minutes",
    "ticketPrice" : 11.0
  },
  "sequenceOfTheDay" : 1,
  "showStartTime" : "28-12-2022 09:00:00",
  "discountedMovieFee" : 8.0
}
{
  "movie" : {
    "title" : "The Batman",
    "movieDuration" : "1 hour 35 minutes",
    "ticketPrice" : 9.0
  },
  "sequenceOfTheDay" : 2,
  "showStartTime" : "28-12-2022 11:00:00",
  "discountedMovieFee" : 6.75
}
{
  "movie" : {
    "title" : "Spider-Man: No Way Home",
    "movieDuration" : "1 hour 30 minutes",
    "ticketPrice" : 12.5
  },
  "sequenceOfTheDay" : 3,
  "showStartTime" : "28-12-2022 12:50:00",
  "discountedMovieFee" : 9.375
}
{
  "movie" : {
    "title" : "Turning Red",
    "movieDuration" : "1 hour 25 minutes",
    "ticketPrice" : 11.0
  },
  "sequenceOfTheDay" : 4,
  "showStartTime" : "28-12-2022 02:30:00",
  "discountedMovieFee" : 8.25
}
{
  "movie" : {
    "title" : "Spider-Man: No Way Home",
    "movieDuration" : "1 hour 30 minutes",
    "ticketPrice" : 12.5
  },
  "sequenceOfTheDay" : 5,
  "showStartTime" : "28-12-2022 04:10:00",
  "discountedMovieFee" : 9.375
}
{
  "movie" : {
    "title" : "The Batman",
    "movieDuration" : "1 hour 35 minutes",
    "ticketPrice" : 9.0
  },
  "sequenceOfTheDay" : 6,
  "showStartTime" : "28-12-2022 05:50:00",
  "discountedMovieFee" : 9.0
}
{
  "movie" : {
    "title" : "Turning Red",
    "movieDuration" : "1 hour 25 minutes",
    "ticketPrice" : 11.0
  },
  "sequenceOfTheDay" : 7,
  "showStartTime" : "28-12-2022 07:30:00",
  "discountedMovieFee" : 10.0
}
{
  "movie" : {
    "title" : "Spider-Man: No Way Home",
    "movieDuration" : "1 hour 30 minutes",
    "ticketPrice" : 12.5
  },
  "sequenceOfTheDay" : 8,
  "showStartTime" : "28-12-2022 09:10:00",
  "discountedMovieFee" : 10.0
}
{
  "movie" : {
    "title" : "The Batman",
    "movieDuration" : "1 hour 35 minutes",
    "ticketPrice" : 9.0
  },
  "sequenceOfTheDay" : 9,
  "showStartTime" : "28-12-2022 11:00:00",
  "discountedMovieFee" : 9.0
}
```
Input :3

Output: 

```
-----------------------------------RESERVATION CONFIRMATION----------------------------------------------------------------------------
CUSTOMER NAME           MOVIE                 SHOW TIME              TICKET PRICE         TICKET COUNT         TOTAL AMOUNT
Nancy               Turning Red          2022-12-28T09:00                  8.0               2                 16.0
---------------------Thank you for your purchase. We look forward to seeing you at the theatre-----------------------------------------

```
