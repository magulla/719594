package com.jpmc.theater;

import static com.jpmc.theater.Constants.REGULAR_MOVIE;
import static com.jpmc.theater.Constants.SPECIAL_MOVIE;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
    	  Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, SPECIAL_MOVIE);
          Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, REGULAR_MOVIE);
          Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, REGULAR_MOVIE);
          LocalDateProvider provider = LocalDateProvider.singleton();
		  List<Showing>showings = List.of(
              new Showing(turningRed, 1, LocalDateTime.of(provider .currentDate(), LocalTime.of(9, 0))),
              new Showing(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
              new Showing(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
              new Showing(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
              new Showing(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
              new Showing(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
              new Showing(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
              new Showing(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
              new Showing(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
          );
		  
        Schedule.Builder builder = new Schedule.Builder().with(provider).with(showings);
		SchedulePrinter printer = new TextSchedulePrinter() ;
		Theater theater = new Theater(builder.build(),printer);
        theater.printSchedule();
    }
}
