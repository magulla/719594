package com.jpmc.theater;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.jpmc.theater.utils.TextUtils;

public class TextSchedulePrinter implements SchedulePrinter{
	@Override
    public void printSchedule(Schedule schedule) {
        System.out.println(schedule.currentDate());
        System.out.println("===================================================");
        schedule.getShowings().forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + TextUtils.humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieFee())
        );
        System.out.println("===================================================");
    }
}
