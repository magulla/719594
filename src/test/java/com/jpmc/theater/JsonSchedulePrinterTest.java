package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import com.jpmc.theater.data.TestDataProvider;
import com.jpmc.theater.printer.JsonSchedulePrinter;

public class JsonSchedulePrinterTest {
	
	@Test 
	public void testPrinter() {
		JsonSchedulePrinter jsonSchedulePrinter = new JsonSchedulePrinter();
        Schedule schedule = new Schedule.Builder().with(TestDataProvider.showings).build();

		jsonSchedulePrinter.printSchedule(schedule);
	}
}

