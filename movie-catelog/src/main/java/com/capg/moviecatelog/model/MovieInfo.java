package com.capg.moviecatelog.model;




public class MovieInfo {

	private int movieId;
	private String movieTitle;
	private String movieDescription;
	private int port;
	
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public MovieInfo() {
		// TODO Auto-generated constructor stub
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

	public MovieInfo(int movieId, String movieTitle, String movieDescription) {
		super();
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.movieDescription = movieDescription;
	}

	@Override
	public String toString() {
		return "MovieInfo [movieId=" + movieId + ", movieTitle=" + movieTitle + ", movieDescription=" + movieDescription
				+ "]";
	}
	
	
	
}
