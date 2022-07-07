package com.jpmc.theater;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FeeCaclulatorTest {
	
	@Mock DiscountCalculator discountCalculator;
	@Mock Showing showing;
	
	@Test
    void priceCalculatedForTwoTicketsWithDiscount() {
		BigDecimal  expectedDiscountPerTicket = new BigDecimal(1);
        final int ticketsCount = 3 ;
        BigDecimal  ticketPrice = new BigDecimal("10");
        Mockito.when(discountCalculator.getDiscount(showing)).thenReturn(expectedDiscountPerTicket);
        Mockito.when(showing.getMovieFee()).thenReturn(ticketPrice);
        
		FeeCalculator feeCalculator = new FeeCalculator();
		BigDecimal actualFee = feeCalculator.calculate(discountCalculator, showing, ticketsCount );
		BigDecimal expected = new BigDecimal(27);
		assertThat(actualFee, equalTo(expected));
    }
    private Showing createTestShowing(int ticketPrice, int sequenceOfTheDay, int specialCode) {
		Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),new BigDecimal(ticketPrice), specialCode);
		Showing showing = new Showing(spiderMan, sequenceOfTheDay, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		return showing;
	}
    
    double ticketPrice_100 = 100;
    int sequenceOfTheDay_5 = 5;
    int sequenceOfTheDay_2 = 2;
    int sequenceOfTheDay_1 = 1;
}
