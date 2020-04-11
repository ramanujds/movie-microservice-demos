package com.capg.movierating.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.movierating.exception.DuplicateMovieException;
import com.capg.movierating.model.MovieRating;
import com.capg.movierating.repo.MovieRatingRepo;

@Service
public class MovieRatingService {
	@Autowired
	MovieRatingRepo repo;
	
	public MovieRating getMovieRatingById(int id) {
		return repo.getOne(id);
	}
	public MovieRating addNewMovieRating(MovieRating rating) {
		if(repo.existsById(rating.getMovieId()))
		{
			throw new DuplicateMovieException("Movie with id : ["+rating.getMovieId()+"] already Exists");
		}
		return repo.save(rating);
	}
}
