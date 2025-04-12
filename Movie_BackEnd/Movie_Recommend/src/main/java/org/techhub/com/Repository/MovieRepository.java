package org.techhub.com.Repository;

import java.util.List;

import org.techhub.com.Model.MovieInfo;

public interface MovieRepository {
	
	public boolean addMovie(MovieInfo movie);
	
	public List<MovieInfo> viewAllMovies();
	
	public List<MovieInfo> searchByGenre(String genre);
	
	public List<MovieInfo> searchByActor(String actor);
	
	public List<MovieInfo> searchByMulti(String actor,String genre,int year);
	
	public boolean deleteById(int movie_id);
	
	public boolean updatemovie(MovieInfo movie);
	
	public List<MovieInfo> getTopRatedMovies();
}
