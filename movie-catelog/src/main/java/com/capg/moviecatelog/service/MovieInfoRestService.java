package com.capg.moviecatelog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capg.moviecatelog.exceptions.DuplicateMovieException;
import com.capg.moviecatelog.model.MovieInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoRestService {

	@Autowired
	RestTemplate rt;
	@HystrixCommand(fallbackMethod = "getMovieInfoFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	public ResponseEntity<MovieInfo> getMovieInfo(int id) {
		ResponseEntity<MovieInfo> infoEntity=rt.getForEntity("http://movie-info-service/movieinfo/id/"+id, MovieInfo.class);
		return infoEntity;
	}
	
	public ResponseEntity<MovieInfo> getMovieInfoFallback(int id){
		MovieInfo info=new MovieInfo(id,"Not Available","Not Available");
		ResponseEntity<MovieInfo> infoEntity=new ResponseEntity<MovieInfo>(info,HttpStatus.OK);
		return infoEntity;
	}
	
	
	
	@HystrixCommand(fallbackMethod = "addMovieInfoFallback", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
	})
	public ResponseEntity<MovieInfo> addMovieInfo(MovieInfo movie) {
		ResponseEntity<MovieInfo> infoEntity=rt.postForEntity("http://movie-info-service/movieinfo/add/",movie, MovieInfo.class);
		if(infoEntity.getStatusCode()!=HttpStatus.CREATED) {
			throw new DuplicateMovieException("Movie with id : ["+movie.getMovieId()+"] already Exists");
		}
		return infoEntity;
	}
	
	public ResponseEntity<MovieInfo> addMovieInfoFallback(MovieInfo movie){
		MovieInfo info=new MovieInfo(movie.getMovieId(),"Not Available","Not Available");
		ResponseEntity<MovieInfo> infoEntity=new ResponseEntity<MovieInfo>(info,HttpStatus.OK);
		return infoEntity;
	}
}
