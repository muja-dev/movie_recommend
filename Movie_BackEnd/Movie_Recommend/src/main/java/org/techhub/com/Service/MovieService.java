package org.techhub.com.Service;

import java.util.List;

import org.techhub.com.Model.MovieInfo;

public interface MovieService {
	
	public boolean addMovie(MovieInfo movie);
	
	public List<MovieInfo> viewAllMovies();
	
	public List<MovieInfo> searchByGenre(String genre);
	
	public List<MovieInfo> searchByActor(String actor);

	public List<MovieInfo> searchByMulti(String actor,String genre,int year);


	
}
