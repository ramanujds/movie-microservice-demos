package com.capg.movieinfo.controller;

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

import com.capg.movieinfo.model.MovieInfo;
import com.capg.movieinfo.service.MovieInfoService;

@RestController
@RequestMapping("/movieinfo")
public class MovieInfoController {
	@Autowired
	MovieInfoService service;
	@Autowired
	Environment env;

@GetMapping("/id/{id}")
 public ResponseEntity<MovieInfo> getMovieInfoById(@PathVariable("id") int id) {
	ResponseEntity<MovieInfo> reMovieInfo;
	MovieInfo info=service.getMovieById(id);
	int port=Integer.parseInt(env.getProperty("local.server.port"));
	
	if(info!=null) {
		info.setPort(port);
		reMovieInfo=new ResponseEntity<>(info,HttpStatus.OK);
	}
	else {
		
		reMovieInfo=new ResponseEntity<>(new MovieInfo(),HttpStatus.CREATED);
	}
	
	 return reMovieInfo;
 }

@PostMapping("/add")
public ResponseEntity<MovieInfo> addNewMovieInfo(@RequestBody MovieInfo movie) {
	MovieInfo info= service.addNewMovieInfo(movie);
	if(info==null) {
		return new ResponseEntity<MovieInfo>(HttpStatus.NOT_ACCEPTABLE);
	}
	return new ResponseEntity<MovieInfo>(info,HttpStatus.CREATED);
}
}
