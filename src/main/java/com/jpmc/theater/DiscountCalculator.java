package com.jpmc.theater;

public class DiscountCalculator {
	
	private DiscountCalculator(){}
	public static DiscountCalculator create() {
		return new DiscountCalculator();
	}
    public  double getDiscount(Showing showing) {
    	Movie movie = showing.getMovie();
        double specialDiscount = 0;
        if (isSpecial(movie)) {
            specialDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (isFirst(showing)) {
            sequenceDiscount = 3; 
        } else if (isSecond(showing)) {
            sequenceDiscount = 2;
        }

        // biggest discount wins
        return specialDiscount > sequenceDiscount ? specialDiscount : sequenceDiscount;
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
