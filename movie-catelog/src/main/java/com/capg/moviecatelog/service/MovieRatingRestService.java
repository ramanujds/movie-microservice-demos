package com.capg.moviecatelog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capg.moviecatelog.exceptions.DuplicateMovieException;
import com.capg.moviecatelog.model.MovieRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieRatingRestService {

	@Autowired
	RestTemplate rt;
	@HystrixCommand(fallbackMethod = "getMovieRatingFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	public ResponseEntity<MovieRating> getMovieRating(int id) {
		ResponseEntity<MovieRating> ratingEntity=rt.getForEntity("http://movie-rating-service/movierating/id/"+id, MovieRating.class);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ratingEntity;
	}
	public ResponseEntity<MovieRating> getMovieRatingFallback(int id) {
		MovieRating rating=new MovieRating(id,0.0);
		ResponseEntity<MovieRating> ratingEntity=new ResponseEntity<MovieRating>(rating,HttpStatus.OK);
		return ratingEntity;
	}
	
	
	
	
	@HystrixCommand(fallbackMethod = "addMovieRatingFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	public ResponseEntity<MovieRating> addMovieRating(MovieRating movie ) {
		ResponseEntity<MovieRating> ratingEntity=rt.postForEntity("http://movie-rating-service/movierating/add",movie, MovieRating.class);
		
		return ratingEntity;
	}
	public ResponseEntity<MovieRating> addMovieRatingFallback(MovieRating movie) {
		MovieRating rating=new MovieRating(movie.getMovieId(),0.0);
		ResponseEntity<MovieRating> ratingEntity=new ResponseEntity<MovieRating>(rating,HttpStatus.OK);
		if(ratingEntity.getStatusCode()!=HttpStatus.CREATED) {
			throw new DuplicateMovieException("Movie with id : ["+movie.getMovieId()+"] already Exists");
		}
		return ratingEntity;
	}
}
