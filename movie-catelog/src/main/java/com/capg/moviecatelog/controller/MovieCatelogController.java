package com.capg.moviecatelog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capg.moviecatelog.model.CatelogItem;
import com.capg.moviecatelog.model.MovieInfo;
import com.capg.moviecatelog.model.MovieRating;
import com.capg.moviecatelog.service.MovieInfoRestService;
import com.capg.moviecatelog.service.MovieRatingRestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/movies")
public class MovieCatelogController {

	@Autowired
	RestTemplate rt;
	@Autowired
	MovieRatingRestService ratingService;
	@Autowired
	MovieInfoRestService infoService;
	
	@GetMapping("/id/{id}")
	//@HystrixCommand(fallbackMethod ="getMovieCatelogItemFallback" )
	public ResponseEntity<CatelogItem> getMovieCatelogItem(@PathVariable int id) {

		ResponseEntity<MovieRating> ratingEntity=ratingService.getMovieRating(id);

		ResponseEntity<MovieInfo> infoEntity=infoService.getMovieInfo(id);
		
		
		MovieInfo info=infoEntity.getBody();
		MovieRating rating=ratingEntity.getBody();
		
		
		ResponseEntity<CatelogItem> catelogEntity;
		CatelogItem item;

			item=new CatelogItem(id,info.getMovieTitle(),info.getMovieDescription(),rating.getRating());
			item.setInfoServicePort(info.getPort());
			item.setRatingServicePort(rating.getPort());
			catelogEntity=new ResponseEntity<>(item,HttpStatus.OK);
		
		return catelogEntity;
	}
//	public ResponseEntity<CatelogItem> getMovieCatelogItemFallback(@PathVariable int id){
//		CatelogItem item=new CatelogItem(0,"Not Available", "Not Available", 0.0);
//		return new ResponseEntity<CatelogItem>(item, HttpStatus.OK);
//	}
	
	
	@PostMapping("/add")
	public ResponseEntity<CatelogItem> addNewCatelogItem(@RequestBody CatelogItem item) {
		MovieInfo info=new MovieInfo(item.getMovieId(),item.getMovieTitle(),item.getMovieDescription());
		MovieRating rating=new MovieRating(item.getMovieId(),item.getRating());
		ResponseEntity<MovieInfo> infoEntity=infoService.addMovieInfo(info);
		ResponseEntity<MovieRating> ratingEntity=ratingService.addMovieRating(rating);
		if(ratingEntity.getStatusCode()==HttpStatus.CREATED && infoEntity.getStatusCode()==HttpStatus.CREATED) {
			return new ResponseEntity<CatelogItem>(getMovieCatelogItem(info.getMovieId()).getBody(),HttpStatus.CREATED);
		}
		return new ResponseEntity<CatelogItem>(HttpStatus.NOT_ACCEPTABLE);
	}
	
}
