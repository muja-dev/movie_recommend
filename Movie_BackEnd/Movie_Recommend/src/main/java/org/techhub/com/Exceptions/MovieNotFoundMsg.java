package org.techhub.com.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class MovieNotFoundMsg {
	private String message;
	private int statusCode;
	public MovieNotFoundMsg(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	
}
