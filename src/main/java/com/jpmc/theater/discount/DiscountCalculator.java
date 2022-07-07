package com.jpmc.theater.discount;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmc.theater.Showing;

public class DiscountCalculator {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private List<Discount> discounts;
	
	
	public DiscountCalculator(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public static DiscountCalculator create() {
		List<Discount> discounts = Arrays.<Discount>asList(
				Discount.specialDiscount, 
				Discount.sequenceDiscount, 
				Discount.discountFor7th,
				Discount.discount11to16
		);
		return new DiscountCalculator(discounts);
	}

    public  BigDecimal getDiscount(Showing showing) {
    	Optional<BigDecimal> maxDiscount = discounts.stream()
    			.map((d)->{return d.apply(showing);})
    			.max((b1,b2)->b1.compareTo(b2));
        return maxDiscount.orElse(BigDecimal.ZERO);
    }

	
}
