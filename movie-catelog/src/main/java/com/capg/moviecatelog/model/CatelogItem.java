package com.capg.moviecatelog.model;

public class CatelogItem {

	private int movieId;
	private String movieTitle;
	private String movieDescription;
	private double rating;
	private int ratingServicePort;
	private int infoServicePort;
	
	public int getRatingServicePort() {
		return ratingServicePort;
	}



	public void setRatingServicePort(int ratingServicePort) {
		this.ratingServicePort = ratingServicePort;
	}



	public int getInfoServicePort() {
		return infoServicePort;
	}



	public void setInfoServicePort(int infoServicePort) {
		this.infoServicePort = infoServicePort;
	}



	public CatelogItem() {
		// TODO Auto-generated constructor stub
	}
	


	@Override
	public String toString() {
		return "CatelogItem [movieId=" + movieId + ", movieTitle=" + movieTitle + ", movieDescription="
				+ movieDescription + ", rating=" + rating + "]";
	}



	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}



	public CatelogItem(int movieId, String movieTitle, String movieDescription, double rating) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
		this.rating = rating;
	}



	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}

}
