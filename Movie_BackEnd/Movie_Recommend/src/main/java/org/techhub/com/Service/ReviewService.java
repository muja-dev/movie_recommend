package org.techhub.com.Service;

import java.util.List;

import org.techhub.com.Model.Review;

public interface ReviewService {

	public boolean addReview(Review review);
	
	public boolean updateReview(Review review);

	public List<Review> reviewById(int id);

}
