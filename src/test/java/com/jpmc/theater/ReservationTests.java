package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.discount.DiscountCalculator;

public class ReservationTests {

    @Test
    void totalFeeShouldUseBiggerDiscountOfFirstShowing() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),new BigDecimal("12.5"), 1),
                1,
                LocalDateTime.now()
        );
        Reservation reservation = new Reservation(customer, showing, 3);
		BigDecimal totalFee = reservation.totalFee(new FeeCalculator(), DiscountCalculator.create());
		BigDecimal expected = new BigDecimal("28.5");
		assertTrue(totalFee.compareTo(expected)== 0, String.format("expected [%s], actual [%s]", expected,totalFee));
    }
}
