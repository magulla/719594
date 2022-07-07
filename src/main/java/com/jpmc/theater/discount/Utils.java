package com.jpmc.theater.discount;

import com.jpmc.theater.Constants;
import com.jpmc.theater.Showing;

class Utils {
    static boolean isSecond(Showing showing) {
		return showing.getSequenceOfTheDay() == 2;
	}

	static boolean isFirst(Showing showing) {
		return showing.getSequenceOfTheDay() == 1;
	}

	static boolean isSpecial(Showing showing) {
		return Constants.SPECIAL_MOVIE == showing.getMovie().getSpecialCode();
	}

}
