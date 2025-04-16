package org.techhub.com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String addReview(@RequestBody Review review)
	{
		boolean b=reviewService.addReview(review);
		return b?"Review added":"not added";
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
