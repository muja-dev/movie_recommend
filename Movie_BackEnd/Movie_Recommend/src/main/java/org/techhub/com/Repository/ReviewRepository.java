package org.techhub.com.Repository;

import java.util.List;

import org.techhub.com.Model.Review;

public interface ReviewRepository {
	public boolean addReview(Review review);
	
	public boolean updateReview(Review review);
	
	public List<Review> reviewById(int id);
}
