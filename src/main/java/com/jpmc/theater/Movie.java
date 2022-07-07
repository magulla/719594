package com.jpmc.theater;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private BigDecimal ticketPrice;
    private int specialCode;


	public Movie(String title, Duration runningTime, BigDecimal ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

	public int getSpecialCode() {
		return specialCode;
	}

	public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

	@Override
	public int hashCode() {
		return Objects.hash(description, runningTime, specialCode, ticketPrice, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(description, other.description) && Objects.equals(runningTime, other.runningTime)
				&& specialCode == other.specialCode && Objects.equals(ticketPrice, other.ticketPrice)
				&& Objects.equals(title, other.title);
	}


}