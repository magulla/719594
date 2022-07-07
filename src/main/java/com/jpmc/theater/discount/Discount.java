package com.jpmc.theater.discount;

import static com.jpmc.theater.discount.Utils.*;

import java.math.BigDecimal;

import com.jpmc.theater.Constants;
import com.jpmc.theater.Showing;

public interface Discount{
	public BigDecimal apply(Showing showing) ;
	
	static Discount specialDiscount = (s)->{
		BigDecimal discountValue = new BigDecimal("0.2");
		if(s.getMovie().getSpecialCode()==Constants.SPECIAL_MOVIE)
			return s.getMovieFee().multiply(discountValue);
		return BigDecimal.ZERO;};
		
	static Discount sequenceDiscount = (s)->{
		if (isFirst(s))
			return  new BigDecimal("3");
		else if (isSecond(s))
			return new BigDecimal("2");
		return BigDecimal.ZERO;};

	static Discount discount11to16 = (s)->{
		BigDecimal discount = new BigDecimal("0.25");
		int hour = s.getStartTime().getHour();
		if(hour>=11 && hour < 16 )
			return s.getMovieFee().multiply(discount);
		return BigDecimal.ZERO;};
		
	static Discount discountFor7th = (s)->{
		int dayOfMonth = s.getStartTime().getDayOfMonth();
		if(dayOfMonth==7)
			return BigDecimal.ONE;
		return BigDecimal.ZERO;};
	
}
