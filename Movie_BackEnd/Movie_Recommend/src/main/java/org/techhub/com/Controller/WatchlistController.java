package org.techhub.com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Model.WatchList;
import org.techhub.com.Service.WatchServiceImpl;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class WatchlistController {
	
	@Autowired
	WatchServiceImpl watchService;
	
	@PostMapping("/addWatchList")
	public ResponseEntity<String> addWatchList(@RequestBody WatchList watch){
		boolean b=watchService.addWatchList(watch);
		if(b)
		{
	        return ResponseEntity.ok("added to watchList successfully");

		}
		else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add");

		}
	}
	
	@GetMapping("/getWatchList/{userId}")
	public ResponseEntity<List<MovieInfo>> getWatchList(@PathVariable int userId) {
	    List<MovieInfo> watchList = watchService.getUserWatchList(userId);
	    return ResponseEntity.ok(watchList);
	}

}
