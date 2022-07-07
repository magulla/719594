package com.jpmc.theater;

public class FeeCalculator {
	public double calculate(DiscountCalculator discountCalculator,Showing showing, int ticketsCount) {
		return showing.getMovie().getTicketPrice() * ticketsCount - discountCalculator.getDiscount(showing); 
	}

}
