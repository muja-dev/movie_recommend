package org.techhub.com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Repository.MovieRepositoryImpl;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepositoryImpl movieRepo;
	
	@Override
	public boolean addMovie(MovieInfo movie) {
		return movieRepo.addMovie(movie);
	}

	@Override
	public List<MovieInfo> viewAllMovies() {
		
		return movieRepo.viewAllMovies();
	}

	@Override
	public List<MovieInfo> searchByGenre(String genre) {
		
		return movieRepo.searchByGenre(genre);
	}

	@Override
	public List<MovieInfo> searchByActor(String actor) {
		
		return movieRepo.searchByActor(actor);
	}

	@Override
	public List<MovieInfo> searchByMulti(String actor, String genre, int year) {
		return movieRepo.searchByMulti(actor, genre, year);
	}

}
