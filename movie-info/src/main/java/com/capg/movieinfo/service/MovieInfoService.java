package com.capg.movieinfo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.movieinfo.exception.DuplicateMovieException;
import com.capg.movieinfo.model.MovieInfo;
import com.capg.movieinfo.repo.MovieInfoRepo;

@Service
public class MovieInfoService {
	@Autowired
	MovieInfoRepo repo;

	public MovieInfo getMovieById(int id) {
		return repo.getOne(id);
	}
	
	public MovieInfo addNewMovieInfo(MovieInfo movie) {
		if(repo.existsById(movie.getMovieId())) {
		throw new DuplicateMovieException("Movie with id : ["+movie.getMovieId()+"] already Exists");
		}
			return repo.save(movie);
	}
}
