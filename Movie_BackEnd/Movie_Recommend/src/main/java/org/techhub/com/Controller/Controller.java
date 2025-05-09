package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Exceptions.MovieNotFound;
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Service.MovieService;
import org.techhub.com.Service.MovieServiceImpl;

@CrossOrigin(origins = "*")
@RestController
public class Controller {

//    private final Service.MovieServiceImpl movieService_1;
	
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
	
	
	@GetMapping("/searchByGenre")
	public List<MovieInfo> searchByGenre(@RequestParam(name = "genre") String genre) {
	    return movieService.searchByGenre(genre);
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
	
	
	@PutMapping("/disable/{movie_id}")
	public ResponseEntity<?> disableMovie(@PathVariable int movie_id) {
	    System.out.println("DISABLING movie with ID: " + movie_id);
	    boolean result = movieService.deleteById(movie_id);
	    System.out.println("Disable result: " + result);
	    return result ? ResponseEntity.ok("Movie disabled")
	                  : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
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

	@GetMapping("/topRatedMovie")
	public List<MovieInfo> getTopRatedMovie()
	{
		List<MovieInfo> list=movieService.getTopRatedMovies();
		if(list.size()>0)
		{
			return list;
		}
		else {
			throw new MovieNotFound("Not found movie");
		}
	}
	
	@GetMapping("/searchByTitle/{title}")
	public List<MovieInfo> searchByTitle(@PathVariable("title") String title)
	{
		return movieService.searchByTitle(title);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
