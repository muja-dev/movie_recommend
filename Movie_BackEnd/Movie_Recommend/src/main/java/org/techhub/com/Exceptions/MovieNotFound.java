package org.techhub.com.Exceptions;

public class MovieNotFound extends RuntimeException{
	private String message;
	public MovieNotFound(String message)
	{
		super(message);
		this.message=message;
	}
}
	