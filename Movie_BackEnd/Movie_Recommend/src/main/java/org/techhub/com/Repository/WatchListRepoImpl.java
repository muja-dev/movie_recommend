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
import org.techhub.com.Model.MovieInfo;
import org.techhub.com.Model.WatchList;

@Repository("watchRepo")
public class WatchListRepoImpl  implements WatchListRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public boolean addWatchList(WatchList watch) {
		
		int value=jdbcTemplate.update("insert into watchlist (user_id,movie_id) values(?,?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setInt(1, watch.getUser_id());
				ps.setInt(2, watch.getMovie_id());
			}
			
		});
		return value>0?true:false;
	}
	@Override
	public List<MovieInfo> getUserWatchList(int user_id) {
	    String sql = "SELECT m.* FROM movies m " +
	                 "JOIN watchlist w ON m.movie_id = w.movie_id " +
	                 "WHERE w.user_id = ?";

	    return jdbcTemplate.query(sql, new Object[]{user_id}, (rs, rowNum) -> {
	        MovieInfo movie = new MovieInfo();
            int movieId = rs.getInt("movie_id");

	        movie.setMovie_id(rs.getInt("movie_id"));
	        movie.setTitle(rs.getString("title"));
	        movie.setDescription(rs.getString("description"));
	        movie.setPoster_url(rs.getString("poster_url"));
	        movie.setYt_link(rs.getString("yt_link"));
	        movie.setRelease_year(rs.getInt("release_year"));
	        movie.setDirector(rs.getString("director"));
	        List<String> actors = jdbcTemplate.query("SELECT a.actor_name FROM actor a " +"JOIN actor_movie am ON a.actor_id = am.actor_id " +"WHERE am.movie_id = ?",
	                new Object[]{movieId},new RowMapper<String>() {

						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("actor_name");
						}
            	
            }
	            );
	            movie.setActor(actors);

	            // Fetch genres for this movie
	            List<String> genres = jdbcTemplate.query( "SELECT g.genre_name FROM genre g " + "JOIN genre_movie gm ON g.genre_id = gm.genre_id " + "WHERE gm.movie_id = ?",
	            		new Object[]{movieId},new RowMapper<String>() {

							@Override
							public String mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getString("genre_name");
							}
	            	
	            }
	            );
	            movie.setGenre(genres);

	            return movie;
	    });
	}

	

}
