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


@Repository("movieRepo")
public class MovieRepositoryImpl implements MovieRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	List<MovieInfo> list;
	@Override
	public boolean addMovie(MovieInfo movie) {
		int value=jdbcTemplate.update("insert into movies values('0',?,?,?,?,?,?,?,?,?,?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, movie.getTitle());
				ps.setString(2, movie.getGenre());
				ps.setInt(3, movie.getRelease_year());
				ps.setString(4, movie.getDirector());
				ps.setFloat(5, movie.getRating());       
				ps.setString(6, movie.getDescription());
				ps.setString(7, movie.getPoster_url());
				ps.setString(8, movie.getHero());
				ps.setString(9, movie.getHeroine());
				ps.setString(10, movie.getYt_link());
			}
			
		});
		return value>0?true:false;
	}

	@Override
	public List<MovieInfo> viewAllMovies() {
		list=jdbcTemplate.query("select *from movies", new RowMapper<MovieInfo>() {

			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				MovieInfo movie=new MovieInfo();
				movie.setMovie_id(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setGenre(rs.getString(3));
				movie.setRelease_year(rs.getInt(4));
				movie.setDirector(rs.getString(5));
				movie.setRating(rs.getFloat(6));
				movie.setDescription(rs.getString(7));
				movie.setPoster_url(rs.getString(8));
				movie.setHero(rs.getString(9));
				movie.setHeroine(rs.getString(10));
				movie.setYt_link(rs.getString(11));
				return movie;
			}
			
		});
		return list.size()>0?list:null;
	}

	@Override
	public List<MovieInfo> searchByGenre(String genre) {
		list=jdbcTemplate.query("select *from movies where genre like ?", new Object[] {genre},new RowMapper<MovieInfo>() {

			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				MovieInfo movie=new MovieInfo();
				movie.setMovie_id(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setGenre(rs.getString(3));
				movie.setRelease_year(rs.getInt(4));
				movie.setDirector(rs.getString(5));
				movie.setRating(rs.getFloat(6));
				movie.setDescription(rs.getString(7));
				movie.setPoster_url(rs.getString(8));
				movie.setHero(rs.getString(9));
				movie.setHeroine(rs.getString(10));
				movie.setYt_link(rs.getString(11));
				return movie;
			}
			
		});
		return list;
	}

	@Override
	public List<MovieInfo> searchByActor(String actor) {
		list=jdbcTemplate.query("select *from movies where hero like ?", new Object[] {actor},new RowMapper<MovieInfo>() {

			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				MovieInfo movie=new MovieInfo();
				movie.setMovie_id(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setGenre(rs.getString(3));
				movie.setRelease_year(rs.getInt(4));
				movie.setDirector(rs.getString(5));
				movie.setRating(rs.getFloat(6));
				movie.setDescription(rs.getString(7));
				movie.setPoster_url(rs.getString(8));
				movie.setHero(rs.getString(9));
				movie.setHeroine(rs.getString(10));
				movie.setYt_link(rs.getString(11));
				return movie;
			}
			
		});
		return list;
	}

	@Override
	public List<MovieInfo> searchByMulti(String actor, String genre, int year) {
		list=jdbcTemplate.query("select *from movies where hero like ? and genre like ? and release_year=?", new Object[] {"%" + actor + "%","%" + genre + "%", year },new RowMapper<MovieInfo>() {

			@Override
			public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				MovieInfo movie=new MovieInfo();
				movie.setMovie_id(rs.getInt(1));
				movie.setTitle(rs.getString(2));
				movie.setGenre(rs.getString(3));
				movie.setRelease_year(rs.getInt(4));
				movie.setDirector(rs.getString(5));
				movie.setRating(rs.getFloat(6));
				movie.setDescription(rs.getString(7));
				movie.setPoster_url(rs.getString(8));
				movie.setHero(rs.getString(9));
				movie.setHeroine(rs.getString(10));
				movie.setYt_link(rs.getString(11));

				return movie;
			}
			 
		});
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
