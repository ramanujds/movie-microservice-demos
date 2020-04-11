package com.capg.movierating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.movierating.model.MovieRating;
import com.capg.movierating.service.MovieRatingService;

@RestController
@RequestMapping("/movierating")
public class MovieRatingController {
	@Autowired
	MovieRatingService service;
	@Autowired
	Environment env;
	
	@GetMapping("/id/{id}")
	public  ResponseEntity<MovieRating> getMovieRatingById(@PathVariable int id) {
		
		MovieRating rating=service.getMovieRatingById(id);
	
		ResponseEntity<MovieRating> movieResponseEntity;
		int port=Integer.parseInt(env.getProperty("local.server.port"));
		
		
		if(rating!=null) {
			rating.setPort(port);
		movieResponseEntity=new ResponseEntity<>(rating,HttpStatus.OK);
		}
		else {
			movieResponseEntity=new ResponseEntity<>(new MovieRating(),HttpStatus.CREATED);
		}
		
		return movieResponseEntity; 
	}
	@PostMapping("/add")
	public ResponseEntity<MovieRating> addNewMvieRating(@RequestBody MovieRating rating) {
		MovieRating movie=service.addNewMovieRating(rating);
		if(movie==null) {
			return new ResponseEntity<MovieRating>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<MovieRating>(movie,HttpStatus.CREATED);
	}

}
