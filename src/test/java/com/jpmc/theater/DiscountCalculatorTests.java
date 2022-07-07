package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTests {
	

    @Test
    void specialMovieWith20PercentDiscount() {
        double expected20Percent = ticketPrice_100/5;
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_5, Constants.SPECIAL_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        double discount = discountCalculator.getDiscount(showing);
        assertEquals(expected20Percent, discount);
    }
    
    @Test
    void firstShowingDiscount() {
    	double expectedDiscount = 3;
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_1, Constants.REGULAR_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        double discount = discountCalculator.getDiscount(showing);
		assertEquals(expectedDiscount, discount);
    }

    @Test
    void secondShowingDiscount() {
    	double expectedDiscount = 2;
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_2, Constants.REGULAR_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        double discount = discountCalculator.getDiscount(showing);
		assertEquals(expectedDiscount, discount);
    }
    
    private Showing createTestShowing(double ticketPrice, int sequenceOfTheDay, int specialCode) {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),ticketPrice, specialCode);
		Showing showing = new Showing(spiderMan, sequenceOfTheDay, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		return showing;
	}
    
    double ticketPrice_100 = 100;
    int sequenceOfTheDay_5 = 5;
    int sequenceOfTheDay_2 = 2;
    int sequenceOfTheDay_1 = 1;
}