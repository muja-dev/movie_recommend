package org.techhub.com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfo {
	private int movie_id;
	private String title;
	private String genre;
	private int release_year;
	private String director;
	private float rating;
	private String description;
	private String poster_url;
	private String hero;
	private String heroine;
	private String yt_link;
}
