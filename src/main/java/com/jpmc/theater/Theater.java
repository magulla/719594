package com.jpmc.theater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpmc.theater.exceptions.NoShowingsForToday;
import com.jpmc.theater.exceptions.NoSuchShowing;

public class Theater {
	Logger logger = LoggerFactory.getLogger(getClass());
	LocalDateProvider provider;
    private Schedule schedule;
    private SchedulePrinter schedulePrinter;

    public Theater(Schedule schedule, SchedulePrinter schedulePrinter) {
    	this.schedule= schedule;
    	this.schedulePrinter = schedulePrinter;
    }


    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        if (schedule.getShowings().isEmpty())
        	throw new NoShowingsForToday();
        try {
            showing = schedule.getShowings().get(sequence - 1);
        } catch (RuntimeException ex) {
        	logger.debug(String.format("No sequence %s in Schedule: %s",sequence,schedule.getShowings() ));
            throw new NoSuchShowing("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printSchedule() {
    	schedulePrinter.printSchedule(schedule);
    }



}
