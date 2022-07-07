package com.jpmc.theater;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscountCalculator {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BigDecimal firstShowingDiscount;
	private BigDecimal secondShowingDiscount;
	private BigDecimal specialDiscountValue;
	private List<Discount> discounts;
	
	
	public DiscountCalculator(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public static DiscountCalculator create() {
		Discount specialDiscount = (s)->{
			BigDecimal discountValue = new BigDecimal("0.2");
			if(s.getMovie().getSpecialCode()==Constants.SPECIAL_MOVIE)
				return s.getMovieFee().multiply(discountValue);
			return BigDecimal.ZERO;};
		Discount sequenceDiscount = (s)->{
			if (isFirst(s))
				return  new BigDecimal("3");
			else if (isSecond(s))
				return new BigDecimal("2");
			return BigDecimal.ZERO;};

		List<Discount> discounts = Arrays.<Discount>asList(
				specialDiscount,
				sequenceDiscount
//				,
//				(s)->{
//					BigDecimal discount = new BigDecimal("2");
//					if(s.getMovie().getSpecialCode()==Constants.SPECIAL_MOVIE)
//						return s.getMovieFee().multiply(discount);
//					return BigDecimal.ZERO;}

		);
		return new DiscountCalculator(discounts);
	}

    public  BigDecimal getDiscount(Showing showing) {
//    	Movie movie = showing.getMovie();
//    	BigDecimal specialDiscount = BigDecimal.ZERO;
//        if (isSpecial(movie)) {
//            specialDiscount = movie.getTicketPrice().multiply(specialDiscountValue );  // 20% discount for special movie
//        }
//
//        BigDecimal sequenceDiscount = BigDecimal.ZERO;
//        if (isFirst(showing)) {
//            sequenceDiscount = firstShowingDiscount; 
//        } else if (isSecond(showing)) {
//            sequenceDiscount = secondShowingDiscount;
//        }
//
//        // biggest discount wins
    	Optional<BigDecimal> maxDiscount = discounts.stream()
    			.map((d)->{return d.apply(showing);})
    			.max((b1,b2)->b1.compareTo(b2));
        return maxDiscount.orElse(BigDecimal.ZERO);
    }

	private  static boolean isSecond(Showing showing) {
		return showing.getSequenceOfTheDay() == 2;
	}

	private  static boolean isFirst(Showing showing) {
		return showing.getSequenceOfTheDay() == 1;
	}

	private static boolean isSpecial(Showing showing) {
		return Constants.SPECIAL_MOVIE == showing.getMovie().getSpecialCode();
	}
	
	public interface Discount{
		public BigDecimal apply(Showing showing) ;
	}
}
