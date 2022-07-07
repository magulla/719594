package com.jpmc.theater;

import java.math.BigDecimal;

public class DiscountCalculator {
	
	private BigDecimal firstShowingDiscount;
	private BigDecimal secondShowingDiscount;
	private BigDecimal specialDiscountValue;

	public DiscountCalculator(BigDecimal specialDiscountValue, BigDecimal firstShowingDiscount,
			BigDecimal secondShowingDiscount) {
				this.specialDiscountValue = specialDiscountValue;
				this.firstShowingDiscount = firstShowingDiscount;
				this.secondShowingDiscount = secondShowingDiscount;
	}

	public static DiscountCalculator create() {
		BigDecimal specialDiscountValue = new BigDecimal("0.2");
		BigDecimal firstShowingDiscount = new BigDecimal("3");
		BigDecimal secondShowingDiscount = new BigDecimal("2");
		return new DiscountCalculator(specialDiscountValue,firstShowingDiscount,secondShowingDiscount);
	}

    public  BigDecimal getDiscount(Showing showing) {
    	Movie movie = showing.getMovie();
    	BigDecimal specialDiscount = BigDecimal.ZERO;
        if (isSpecial(movie)) {
            specialDiscount = movie.getTicketPrice().multiply(specialDiscountValue );  // 20% discount for special movie
        }

        BigDecimal sequenceDiscount = BigDecimal.ZERO;
        if (isFirst(showing)) {
            sequenceDiscount = firstShowingDiscount; 
        } else if (isSecond(showing)) {
            sequenceDiscount = secondShowingDiscount;
        }

        // biggest discount wins
        return specialDiscount.compareTo(sequenceDiscount)>=0? specialDiscount : sequenceDiscount;
    }

	private boolean isSecond(Showing showing) {
		return showing.getSequenceOfTheDay() == 2;
	}

	private boolean isFirst(Showing showing) {
		return showing.getSequenceOfTheDay() == 1;
	}

	private boolean isSpecial(Movie movie) {
		return Constants.SPECIAL_MOVIE == movie.getSpecialCode();
	}
	

}
