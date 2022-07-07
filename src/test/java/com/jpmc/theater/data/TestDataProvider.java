package com.jpmc.theater.data;
import static com.jpmc.theater.Constants.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import com.jpmc.theater.LocalDateProvider;
import com.jpmc.theater.Movie;
import com.jpmc.theater.Showing;

public class TestDataProvider {
	  public static  Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, SPECIAL_MOVIE);
	  public static Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, REGULAR_MOVIE);
	  public static Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, REGULAR_MOVIE);
	  public static LocalDateProvider provider = LocalDateProvider.singleton();
	  public static List<Showing>showings = List.of(
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

}
