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
	    // 1. Insert into movies
	    int movieResult = jdbcTemplate.update(
	        "INSERT INTO movies (title, release_year, director, rating, description, poster_url, yt_link) VALUES (?, ?, ?, ?, ?, ?, ?)",
	        ps -> {
	            ps.setString(1, movie.getTitle());
	            ps.setInt(2, movie.getRelease_year());
	            ps.setString(3, movie.getDirector());
	            ps.setFloat(4, movie.getRating());
	            ps.setString(5, movie.getDescription());
	            ps.setString(6, movie.getPoster_url());
	            ps.setString(7, movie.getYt_link());
	        }
	    );

	    // 2. Get the generated movie_id
	    Integer movieId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

	    // 3. Insert actors and link
	    for (String actor : movie.getActor()) {
	        jdbcTemplate.update("INSERT INTO actor (actor_name) VALUES (?)", actor);
	        Integer actorId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	        jdbcTemplate.update("INSERT INTO actor_movie (movie_id, actor_id) VALUES (?, ?)", movieId, actorId);
	    }

	    // 4. Insert genres and link
	    for (String genre : movie.getGenre()) {
	        jdbcTemplate.update("INSERT INTO genre (genre_name) VALUES (?)", genre);
	        Integer genreId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	        jdbcTemplate.update("INSERT INTO genre_movie (movie_id, genre_id) VALUES (?, ?)", movieId, genreId);
	    }

	    return movieResult > 0;
	}


	@Override
	public List<MovieInfo> viewAllMovies() {
	    List<MovieInfo> list = jdbcTemplate.query("SELECT * FROM movies", new RowMapper<MovieInfo>() {
	        @Override
	        public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	            MovieInfo movie = new MovieInfo();
	            int movieId = rs.getInt("movie_id");

	            movie.setMovie_id(movieId);
	            movie.setTitle(rs.getString("title"));
	            movie.setRelease_year(rs.getInt("release_year"));
	            movie.setDirector(rs.getString("director"));
	            movie.setRating(rs.getFloat("rating"));
	            movie.setDescription(rs.getString("description"));
	            movie.setPoster_url(rs.getString("poster_url"));
	            movie.setYt_link(rs.getString("yt_link"));

	            // Fetch actors for this movie
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
	        }
	    });

	    return list.isEmpty() ? null : list;
	}


	@Override
	public List<MovieInfo> searchByGenre(String genre) {
	    list = jdbcTemplate.query(
	        "SELECT DISTINCT m.* FROM movies m " +
	        "JOIN genre_movie gm ON m.movie_id = gm.movie_id " +
	        "JOIN genre g ON g.genre_id = gm.genre_id " +
	        "WHERE g.genre_name LIKE ?",
	        new Object[]{"%" + genre + "%"},
	        new RowMapper<MovieInfo>() {

	            @Override
	            public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	                MovieInfo movie = new MovieInfo();
	                int movieId = rs.getInt("movie_id");

	                movie.setMovie_id(movieId);
	                movie.setTitle(rs.getString("title"));
	                movie.setRelease_year(rs.getInt("release_year"));
	                movie.setDirector(rs.getString("director"));
	                movie.setRating(rs.getFloat("rating"));
	                movie.setDescription(rs.getString("description"));
	                movie.setPoster_url(rs.getString("poster_url"));
	                movie.setYt_link(rs.getString("yt_link"));

	                // Get genres
	                List<String> genres = jdbcTemplate.query(
	                    "SELECT g.genre_name FROM genre g " +
	                    "JOIN genre_movie gm ON g.genre_id = gm.genre_id " +
	                    "WHERE gm.movie_id = ?",
	                    new Object[]{movieId},
	                    (genreRs, i) -> genreRs.getString("genre_name")
	                );
	                movie.setGenre(genres);

	                // Get actors
	                List<String> actors = jdbcTemplate.query(
	                    "SELECT a.actor_name FROM actor a " +
	                    "JOIN actor_movie am ON a.actor_id = am.actor_id " +
	                    "WHERE am.movie_id = ?",
	                    new Object[]{movieId},
	                    (actorRs, i) -> actorRs.getString("actor_name")
	                );
	                movie.setActor(actors);

	                return movie;
	            }
	        });

	    return list;
	}


	@Override
	public List<MovieInfo> searchByActor(String actor) {
	    list = jdbcTemplate.query(
	        "SELECT DISTINCT m.* FROM movies m " +
	        "JOIN actor_movie am ON m.movie_id = am.movie_id " +
	        "JOIN actor a ON a.actor_id = am.actor_id " +
	        "WHERE a.actor_name LIKE ?",
	        new Object[]{"%" + actor + "%"},
	        new RowMapper<MovieInfo>() {

	            @Override
	            public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
	                MovieInfo movie = new MovieInfo();
	                int movieId = rs.getInt("movie_id");

	                movie.setMovie_id(movieId);
	                movie.setTitle(rs.getString("title"));
	                movie.setRelease_year(rs.getInt("release_year"));
	                movie.setDirector(rs.getString("director"));
	                movie.setRating(rs.getFloat("rating"));
	                movie.setDescription(rs.getString("description"));
	                movie.setPoster_url(rs.getString("poster_url"));
	                movie.setYt_link(rs.getString("yt_link"));

	                // Get genres
	                List<String> genres = jdbcTemplate.query(
	                    "SELECT g.genre_name FROM genre g " +
	                    "JOIN genre_movie gm ON g.genre_id = gm.genre_id " +
	                    "WHERE gm.movie_id = ?",
	                    new Object[]{movieId},
	                    (genreRs, i) -> genreRs.getString("genre_name")
	                );
	                movie.setGenre(genres);

	                // Get actors
	                List<String> actors = jdbcTemplate.query(
	                    "SELECT a.actor_name FROM actor a " +
	                    "JOIN actor_movie am ON a.actor_id = am.actor_id " +
	                    "WHERE am.movie_id = ?",
	                    new Object[]{movieId},
	                    (actorRs, i) -> actorRs.getString("actor_name")
	                );
	                movie.setActor(actors);

	                return movie;
	            }
	        });

	    return list;
	}


	@Override
	public List<MovieInfo> searchByMulti(String actor, String genre, int year) {
		list = jdbcTemplate.query(
			"SELECT DISTINCT m.* FROM movies m " +
			"JOIN actor_movie am ON m.movie_id = am.movie_id " +
			"JOIN actor a ON am.actor_id = a.actor_id " +
			"JOIN genre_movie gm ON m.movie_id = gm.movie_id " +
			"JOIN genre g ON gm.genre_id = g.genre_id " +
			"WHERE a.actor_name LIKE ? AND g.genre_name LIKE ? AND m.release_year = ?",
			new Object[] { "%" + actor + "%", "%" + genre + "%", year },
			new RowMapper<MovieInfo>() {

				@Override
				public MovieInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					MovieInfo movie = new MovieInfo();
					int movieId = rs.getInt("movie_id");

					movie.setMovie_id(movieId);
					movie.setTitle(rs.getString("title"));
					movie.setRelease_year(rs.getInt("release_year"));
					movie.setDirector(rs.getString("director"));
					movie.setRating(rs.getFloat("rating"));
					movie.setDescription(rs.getString("description"));
					movie.setPoster_url(rs.getString("poster_url"));
					movie.setYt_link(rs.getString("yt_link"));

					// Fetch genres
					List<String> genres = jdbcTemplate.query(
						"SELECT g.genre_name FROM genre g JOIN genre_movie gm ON g.genre_id = gm.genre_id WHERE gm.movie_id = ?",
						new Object[] { movieId },
						(rs1, rowNum1) -> rs1.getString("genre_name")
					);
					movie.setGenre(genres);

					// Fetch actors
					List<String> actors = jdbcTemplate.query(
						"SELECT a.actor_name FROM actor a JOIN actor_movie am ON a.actor_id = am.actor_id WHERE am.movie_id = ?",
						new Object[] { movieId },
						(rs2, rowNum2) -> rs2.getString("actor_name")
					);
					movie.setActor(actors);

					return movie;
				}
			}
		);

		return list;
	}


	@Override
	public boolean deleteByName(String title) {
	    try {
	        // Step 1: Get movie_id(s) for the given title
	        List<Integer> movieIds = jdbcTemplate.query(
	            "SELECT movie_id FROM movies WHERE title = ?",
	            new Object[]{title},
	            (rs, rowNum) -> rs.getInt("movie_id")
	        );

	        if (movieIds.isEmpty()) {
	            return false; // No movie found
	        }

	        // Step 2: Delete from join tables
	        for (int movieId : movieIds) {
	            jdbcTemplate.update("DELETE FROM actor_movie WHERE movie_id = ?", movieId);
	            jdbcTemplate.update("DELETE FROM genre_movie WHERE movie_id = ?", movieId);
	        }

	        // Step 3: Delete from movies table
	        int rowsAffected = jdbcTemplate.update(
	            "DELETE FROM movies WHERE title = ?",
	            title
	        );

	        return rowsAffected > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	@Override
	public boolean updatemovie(MovieInfo movie) {
	    int updated = jdbcTemplate.update(
	        "UPDATE movies SET title=?, release_year=?, director=?, rating=?, description=?, poster_url=?, yt_link=? WHERE movie_id=?",
	        movie.getTitle(),
	        movie.getRelease_year(),
	        movie.getDirector(),
	        movie.getRating(),
	        movie.getDescription(),
	        movie.getPoster_url(),
	        movie.getYt_link(),
	        movie.getMovie_id()
	    );

	    if (updated > 0) {
	        // Delete old relationships from join tables
	        jdbcTemplate.update("DELETE FROM actor_movie WHERE movie_id=?", movie.getMovie_id());
	        jdbcTemplate.update("DELETE FROM genre_movie WHERE movie_id=?", movie.getMovie_id());

	        // Insert updated actors
	        for (String actorName : movie.getActor()) {
	            List<Integer> actorIds = jdbcTemplate.query(
	                "SELECT actor_id FROM actor WHERE actor_name=?",
	                new Object[]{actorName},
	                (rs, rowNum) -> rs.getInt("actor_id")
	            );

	            for (Integer actorId : actorIds) {
	                jdbcTemplate.update("INSERT INTO actor_movie(actor_id, movie_id) VALUES(?, ?)", actorId, movie.getMovie_id());
	            }

	            if (actorIds.isEmpty()) {
	                System.err.println("No actor found for name: " + actorName);
	            }
	        }

	        // Insert updated genres
	        for (String genreName : movie.getGenre()) {
	            List<Integer> genreIds = jdbcTemplate.query(
	                "SELECT genre_id FROM genre WHERE genre_name=?",
	                new Object[]{genreName},
	                (rs, rowNum) -> rs.getInt("genre_id")
	            );

	            for (Integer genreId : genreIds) {
	                jdbcTemplate.update("INSERT INTO genre_movie(genre_id, movie_id) VALUES(?, ?)", genreId, movie.getMovie_id());
	            }

	            if (genreIds.isEmpty()) {
	                System.err.println("No genre found for name: " + genreName);
	            }
	        }

	        return true;
	    }

	    return false;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
