package com.capg.moviecatelog.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capg.moviecatelog.exceptions.DuplicateMovieException;
import com.capg.moviecatelog.exceptions.ExceptionResponse;
import com.capg.moviecatelog.exceptions.MovieNotFoundException;


@ControllerAdvice
@RestController
public class MoovieCatelogErrorController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MovieNotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex,WebRequest request){
		ExceptionResponse res=new ExceptionResponse(new Date(),ex.getLocalizedMessage(),request.getDescription(false));
		return new ResponseEntity(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateMovieException.class)
	public final ResponseEntity<ExceptionResponse> handlenDuplicateMovie(Exception ex,WebRequest request){
		ExceptionResponse res=new ExceptionResponse(new Date(),ex.getLocalizedMessage(),request.getDescription(false));
		return new ResponseEntity<>(res,HttpStatus.NOT_ACCEPTABLE);
	}
	
}


