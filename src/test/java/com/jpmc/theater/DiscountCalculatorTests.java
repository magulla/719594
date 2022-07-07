package com.jpmc.theater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.discount.DiscountCalculator;

public class DiscountCalculatorTests {
	

    @Test
    void specialMovieWith20PercentDiscount() {
        BigDecimal expected20Percent = new BigDecimal(100).divide(new BigDecimal(5));
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_5, Constants.SPECIAL_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        BigDecimal discount = discountCalculator.getDiscount(showing);
        assertThat(String.format("actual[%s] vs [%s]", discount,expected20Percent),discount.compareTo(expected20Percent), is(0));
    }
    
    @Test
    void firstShowingDiscount() {
    	BigDecimal expectedDiscount = new BigDecimal(3);
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_1, Constants.REGULAR_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        BigDecimal discount = discountCalculator.getDiscount(showing);
        assertThat(String.format("actual[%s] vs [%s]", discount,expectedDiscount),discount.compareTo(expectedDiscount), is(0));
    }

    @Test
    void secondShowingDiscount() {
    	BigDecimal expectedDiscount = new BigDecimal(2);
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_2, Constants.REGULAR_MOVIE);
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        BigDecimal discount = discountCalculator.getDiscount(showing);
        assertThat(String.format("actual[%s] vs [%s]", discount,expectedDiscount),discount.compareTo(expectedDiscount), is(0));
    }

    @Test
    void timeFrom11To16Discount() {
    	BigDecimal expectedDiscount = new BigDecimal("25");
		LocalDateTime dateTime = LocalDateTime.of(2025, 5, 1, 12, 55);
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_5, Constants.REGULAR_MOVIE, dateTime );
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        BigDecimal discount = discountCalculator.getDiscount(showing);
        assertThat(String.format("actual[%s] vs [%s]", discount,expectedDiscount),discount.compareTo(expectedDiscount), is(0));
    }

    @Test
    void theDateOf7thDiscount() {
    	BigDecimal expectedDiscount = new BigDecimal(1);
		LocalDateTime dateTime = LocalDateTime.of(2025, 5, 7, 17, 55);
		Showing showing = createTestShowing(ticketPrice_100, sequenceOfTheDay_5, Constants.REGULAR_MOVIE, dateTime );
        DiscountCalculator discountCalculator = DiscountCalculator.create();
        BigDecimal discount = discountCalculator.getDiscount(showing);
        assertThat(String.format("actual[%s] vs [%s]", discount,expectedDiscount),
        		discount.compareTo(expectedDiscount), is(0));
        
    }

    private Showing createTestShowing(String  ticketPrice, int sequenceOfTheDay, int specialCode) {
    	LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		return createTestShowing(ticketPrice, sequenceOfTheDay, specialCode, dateTime);
	}

	private Showing createTestShowing(String ticketPrice, int sequenceOfTheDay, int specialCode, LocalDateTime dateTime) {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),new BigDecimal(ticketPrice), specialCode);
		Showing showing = new Showing(spiderMan, sequenceOfTheDay, dateTime);
		return showing;
	}
    
    String ticketPrice_100 = "100";
    int sequenceOfTheDay_5 = 5;
    int sequenceOfTheDay_2 = 2;
    int sequenceOfTheDay_1 = 1;
}
