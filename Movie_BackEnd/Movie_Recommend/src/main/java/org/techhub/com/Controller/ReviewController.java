package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.techhub.com.Model.Review;
import org.techhub.com.Service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	@PostMapping("/addReview")
	public ResponseEntity<String> addReview(@RequestBody Review review) {
	    boolean isAdded = reviewService.addReview(review); // Your addReview method in the service layer
	    if (isAdded) {
	        return ResponseEntity.ok("Review added successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add review");
	    }
	}


	
	@PutMapping("/updateReview")
	public String updateReview(@RequestBody Review review)
	{
		boolean b=reviewService.updateReview(review);
		return b?"Review added":"not added";

	}
	
	@GetMapping("/reviewById/{id}")
	public List<Review> reviewById(@PathVariable("id") int id) {
	    return reviewService.reviewById(id);
	}

}
