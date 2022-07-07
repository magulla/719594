package com.jpmc.theater;

import static com.jpmc.theater.data.TestDataProvider.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jpmc.theater.data.TestDataProvider;
import com.jpmc.theater.exceptions.NoShowingsForToday;
import com.jpmc.theater.exceptions.NoSuchShowing;

@ExtendWith(MockitoExtension.class)
public class TheaterTests {

	private static final List<Showing> NO_SHOWINGS = Collections.emptyList();
	private TextSchedulePrinter textPrinter = new TextSchedulePrinter();
	private Schedule.Builder builder = new Schedule.Builder()
			.with(showings);

	@Mock
	private Schedule mockSchedule;
	@BeforeEach
	public void setup() {
		builder = new Schedule.Builder()
				.with(TestDataProvider.showings);
	}
	
	@Test
	void printMovieSchedule() {
		Theater theater = new Theater(builder.build(), textPrinter);
		theater.printSchedule();
	}

	@Test()
	void whenShowingsPresent_thenShouldBeAbleToReserve() {
		Mockito.when(mockSchedule.getShowings()).thenReturn(TestDataProvider.showings);
		Theater theater = new Theater(mockSchedule, textPrinter);
		Customer john = new Customer("John Doe", "id-1");
		Reservation reservation = theater.reserve(john, 2, 1);
		assertThat(reservation, notNullValue());
	}

	@Test
	void whenNoShowingsPresent_thenErrorThrownOnReserve() {
		Mockito.when(mockSchedule.getShowings()).thenReturn(NO_SHOWINGS);
		Theater theater = new Theater(mockSchedule, textPrinter);
		Customer john = new Customer("John Doe", "id-1");
		try {
			Reservation reservation = theater.reserve(john, 2, 1);
			Assertions.fail("Exception was expected");
		} catch (Exception e) {
			assertThat("NoShowingsForToday exception is expected, but was "+e.getClass(), e instanceof NoShowingsForToday);
		}
	}
	
	@Test
	void whenNoSequenceInShowingsPresent_thenErrorThrownOnReserve() {
		int noExistingSequence = 100;
		Mockito.when(mockSchedule.getShowings()).thenReturn(TestDataProvider.showings);
		Theater theater = new Theater(mockSchedule, textPrinter);
		Customer john = new Customer("John Doe", "id-1");
		try {
			Reservation reservation = theater.reserve(john, noExistingSequence, 1);
			Assertions.fail("Exception was expected");
		} catch (Exception e) {
			assertThat("NoSuchShowing exception is expected, but was "+e.getClass(), e instanceof NoSuchShowing);
		}
	}

	
}
