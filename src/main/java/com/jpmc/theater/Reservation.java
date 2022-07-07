package com.jpmc.theater;

import java.math.BigDecimal;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    public BigDecimal totalFee(FeeCalculator feeCalculator, DiscountCalculator discountCalculator) {
        return feeCalculator.calculate(discountCalculator, showing, audienceCount);
    }

	@Override
	public String toString() {
		return "Reservation [customer=" + customer + ", showing=" + showing + ", audienceCount=" + audienceCount + "]";
	}
    
}