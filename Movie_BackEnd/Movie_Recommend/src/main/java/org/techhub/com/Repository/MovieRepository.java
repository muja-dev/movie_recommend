package org.techhub.com.Repository;

import java.util.List;

import org.techhub.com.Model.MovieInfo;

public interface MovieRepository {
	
	public boolean addMovie(MovieInfo movie);
	
	public List<MovieInfo> viewAllMovies();
	
	public List<MovieInfo> searchByGenre(String genre);
}
