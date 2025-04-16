package org.techhub.com.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.techhub.com.Model.Review;
import org.techhub.com.Repository.ReviewRepository;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	private ReviewRepository reviewRepo;
	@Override
	public boolean addReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepo.addReview(review);
	}
	@Override
	public boolean updateReview(Review review) {
		// TODO Auto-generated method stub
		return reviewRepo.updateReview(review);
	}
	@Override
	public List<Review> reviewById(int id) {
		// TODO Auto-generated method stub
		return reviewRepo.reviewById(id);
	}

}
