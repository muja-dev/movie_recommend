package org.techhub.com.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.techhub.com.Model.Review;

@Repository("reviewRepo")
public class ReviewRepositoryImpl implements ReviewRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	List<Review> list;
	@Override
	public boolean addReview(Review review) {
	    try {
	        System.out.println("Review being inserted:");
	        System.out.println("User ID: " + review.getUser_id());
	        System.out.println("Movie ID: " + review.getMovie_id());
	        System.out.println("Rating: " + review.getRating());
	        System.out.println("Review: " + review.getReview());

	        int value = jdbcTemplate.update("INSERT INTO ratings (user_id, movie_id, rating, review) VALUES (?, ?, ?, ?)",
	            new PreparedStatementSetter() {
	                @Override
	                public void setValues(PreparedStatement ps) throws SQLException {
	                    ps.setInt(1, review.getUser_id());
	                    ps.setInt(2, review.getMovie_id());
	                    ps.setFloat(3, review.getRating());
	                    ps.setString(4, review.getReview());
	                }
	            });
	        return value > 0;
	    } catch (Exception e) {
	        System.err.println("Error inserting review: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updateReview(Review review) {
		int value=jdbcTemplate.update("update ratings set rating=?,review=? where rating_id=?",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setFloat(1, review.getRating());
				ps.setString(2, review.getReview());
				ps.setInt(3, review.getRating_id());
			}
			
		});
		return value>0;
	}
	@Override
	public List<Review> reviewById(int id) {
        // Execute the query and map each result row to a Review object
        list= jdbcTemplate.query(
            "SELECT m.title, m.poster_url, r.rating_id, r.movie_id, r.user_id, r.rating, r.review, r.created_at, u.username " +
            "FROM ratings r " +
            "INNER JOIN movies m ON r.movie_id = m.movie_id " +
            "INNER JOIN users u ON r.user_id = u.user_id " +
            "WHERE u.user_id = ?",
            new Object[]{id},  // Passing the user ID as a parameter
            new RowMapper<Review>() {
                @Override
                public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // Creating a new Review object
                    Review review = new Review();

                    // Mapping the ResultSet to the Review object properties
                    review.setRating_id(rs.getInt("rating_id"));
                    review.setMovie_id(rs.getInt("movie_id"));
                    review.setUser_id(rs.getInt("user_id"));
                    review.setRating(rs.getFloat("rating"));
                    review.setReview(rs.getString("review"));
                    review.setCreated_at(rs.getString("created_at"));
                    
                    // Mapping movie title and poster URL
                    review.setMovieTitle(rs.getString("title"));
                    review.setPosterUrl(rs.getString("poster_url"));
                    
                    // Mapping username
                    review.setUsername(rs.getString("username"));

                    return review;  // Returning the mapped Review object
                }
            }
        ); 
        // Returning the list of Review objects
        return list.size()>0?list:null;
    }
	
	

}
