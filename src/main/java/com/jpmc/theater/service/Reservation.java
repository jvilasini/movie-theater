package com.jpmc.theater.service;

import com.jpmc.theater.models.Customer;
import com.jpmc.theater.models.Showing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
A reservation is a class with attributes customer, showing, audience count and method to calculate total movie fee
 */
@Getter
@Setter
@AllArgsConstructor
public class Reservation {
	private Customer customer;
	private Showing showing;
	private int audienceCount;

	public double totalFee()
	{
		return showing.getDiscountedMovieFee() * audienceCount;
	}

	@Override
	public String toString() {
		return "Reservation Confirmed for the customer " + customer.getName() + ", for the Show '"
				+ showing.getMovie().getTitle() + "' at show time " + showing.getShowStartTime() + "  for "
				+ audienceCount + " audience with TotalFee of $" + totalFee();
	}
}