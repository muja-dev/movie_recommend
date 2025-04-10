package org.techhub.com.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionMovie {
	@ExceptionHandler(value=MovieNotFound.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody MovieNotFoundMsg handleMovieException(MovieNotFound exception)
	{
		return new MovieNotFoundMsg(exception.getMessage(),HttpStatus.CONFLICT.value());
	}
}
