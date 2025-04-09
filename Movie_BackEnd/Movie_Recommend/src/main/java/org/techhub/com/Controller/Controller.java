package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Service.MovieService;

@RestController
public class Controller {
	
	@Autowired
	MovieService movieService;
	
	@PostMapping("/addmovie")
	public String addMovie(@RequestBody MovieInfo movie) {
		boolean b=movieService.addMovie(movie);
		if(b)
		{
			return "movie added";
		}
		else {
			return "Problem";
		}
	}
	
	@GetMapping("/viewAllMovies")
	public List<MovieInfo> viewAllMovies(){
		List<MovieInfo> list=movieService.viewAllMovies();
		if(list.size()>0)
		{
			return list;
		}
		else {
			return null;
		}
	}
	
	
	@GetMapping("/searchByGenre/{genre}")
	public List<MovieInfo> searchByGenre(@PathVariable("genre") String genre){
		List<MovieInfo> list=movieService.searchByGenre(genre);
		if(list.size()>0)
		{
			return list;
		}
		else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
