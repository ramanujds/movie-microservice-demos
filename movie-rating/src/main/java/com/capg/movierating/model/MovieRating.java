package com.capg.movierating.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MovieRating {

	@Id
	private int movieId;
	private double rating;
	@Transient
	private int port;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MovieRating() {
		// TODO Auto-generated constructor stub
	}

	public MovieRating(int movieId, double rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "MovieRating [movieId=" + movieId + ", rating=" + rating + "]";
	}
	
	
	
}
