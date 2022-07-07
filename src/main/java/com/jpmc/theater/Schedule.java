package com.jpmc.theater;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schedule {

	private LocalDateProvider localDateProvider;
	private List<Showing> showings;
	private Schedule(LocalDateProvider dateProvider, List<Showing> showings) {
		this.localDateProvider = dateProvider;
		this.showings = showings;
	}
	public LocalDate currentDate() {
		// TODO Auto-generated method stub
		return localDateProvider.currentDate();
	}

	public List<Showing> getShowings() {
		// TODO Auto-generated method stub
		return showings;
	}
	
	@Override
	public String toString() {
		return "Schedule [localDateProvider=" + localDateProvider + ", showings=" + showings + "]";
	}

	public static class Builder {
		private LocalDateProvider dateProvider = LocalDateProvider.singleton();
		private List<Showing> showings = new ArrayList<>();

		public Builder with( Showing showing) {
			return with(Collections.singletonList(showing));
		}

		public Builder with( List<Showing> showings) {
			this.showings.addAll(showings);
			this.showings.sort((e1,e2)->{return e2.getSequenceOfTheDay() - e1.getSequenceOfTheDay();});
			return this;
		}
		
		public Builder with(LocalDateProvider dateProvider) {
			this.dateProvider = dateProvider;
			return this;
		}
		
		public Schedule build() {
			return new Schedule(dateProvider, showings);
		}
	}
}
