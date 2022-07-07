package com.jpmc.theater.exceptions;

public class NoSuchShowing extends RuntimeException{

	public NoSuchShowing(String text) {
		super(text);
	}

	private static final long serialVersionUID = 1L;

}
