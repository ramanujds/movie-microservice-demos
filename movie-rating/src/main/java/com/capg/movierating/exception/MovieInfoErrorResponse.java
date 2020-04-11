package com.capg.movierating.exception;

import java.util.Date;

public class MovieInfoErrorResponse {

	Date date;
	String message;
	String description;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MovieInfoErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	public MovieInfoErrorResponse(Date date, String message, String description) {
		super();
		this.date = date;
		this.message = message;
		this.description = description;
	}
	
}
