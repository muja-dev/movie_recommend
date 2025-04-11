package org.techhub.com.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfo {
	private int movie_id;
	private String title;
	private int release_year;
	private String director;
	private float rating;
	private String description;
	private String poster_url;
	private String yt_link;
	
	private List<String> actor;
	private List<String> genre;
	
}
