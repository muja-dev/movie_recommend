package org.techhub.com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	private int rating_id;
	private int movie_id;
	private int user_id;
	private float rating;
	private String review;
	private String created_at;
	
	private String movieTitle;
    private String posterUrl;
    private String username;
}
