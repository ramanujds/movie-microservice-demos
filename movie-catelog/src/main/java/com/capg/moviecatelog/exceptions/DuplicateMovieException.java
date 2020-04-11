package com.capg.moviecatelog.exceptions;

public class DuplicateMovieException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateMovieException(String message) {
		super(message);
	}
	
}
