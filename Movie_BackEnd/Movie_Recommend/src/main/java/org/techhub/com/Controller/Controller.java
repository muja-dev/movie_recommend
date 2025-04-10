package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Exceptions.MovieNotFound;
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
	
	@GetMapping("/searchByActor/{actor}")
	public List<MovieInfo> searchByActor(@PathVariable("actor") String actor){
		List<MovieInfo> list=movieService.searchByActor(actor);
		if(list.size()>0)
		{
			return list;
		}
		else {
			return null;
		}
	}	
	
	@GetMapping("/searchByMulti/{actor}/{genre}/{year}")
	public List<MovieInfo> searchByMulti(@PathVariable("actor") String actor,@PathVariable("genre") String genre,@PathVariable("year") int year)
	{
		List<MovieInfo> list=movieService.searchByMulti(actor, genre, year);
		if(list.size()>0)
		{
			return list;
		}
		else {
			throw new MovieNotFound("not found");
		}
	}
	
	
	@DeleteMapping("/deleteByName/{title}")
	public String deleteByName(@PathVariable("title") String title) {
		boolean b=movieService.deleteByName(title);
		if(b)
		{
			return "movie deleted";
		}
		else {
			throw new MovieNotFound("Movie not found");
		}
		
	}
	
	@PutMapping("/updatemovie")
	public ResponseEntity<String> updateMovie(@RequestBody MovieInfo movie) {
	    boolean isUpdated = movieService.updatemovie(movie);
	    
	    if (isUpdated) {
	        return ResponseEntity.ok("Movie updated successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found or update failed.");
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
