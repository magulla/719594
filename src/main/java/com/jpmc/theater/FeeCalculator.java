package com.jpmc.theater;

import java.math.BigDecimal;

import com.jpmc.theater.discount.DiscountCalculator;

public class FeeCalculator {
	public BigDecimal calculate(DiscountCalculator discountCalculator,Showing showing, int ticketsCount) {
		BigDecimal discount = discountCalculator.getDiscount(showing);
		return showing.getMovieFee()
				.subtract(discount)
				.multiply(new BigDecimal(ticketsCount)); 
	}

}
