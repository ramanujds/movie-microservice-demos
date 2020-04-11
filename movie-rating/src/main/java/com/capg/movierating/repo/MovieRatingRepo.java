package com.capg.movierating.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capg.movierating.model.MovieRating;

public interface MovieRatingRepo extends JpaRepository<MovieRating, Integer> {

}
