import React, { useEffect, useState } from 'react';
import { getMovies, deleteMovie, updateMovie, searchMovie } from '../api';
import MovieCard from './MovieCard';
import axios from 'axios';

const MovieList = () => {
  const [movies, setMovies] = useState([]);
  const [editingMovie, setEditingMovie] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [isLoading, setIsLoading] = useState(false); // Loading state

  const fetchData = async () => {
    setIsLoading(true);
    try {
      const res = await getMovies();
      setMovies(res.data);
    } catch (err) {
      console.error("Failed to fetch movies:", err);
      alert("Failed to load movies.");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDisable = async (movie_id) => {
    if (!window.confirm("Are you sure you want to disable this movie?")) return;
  
    setIsLoading(true); // Show loading indicator during request
    try {
      await axios.put(`http://localhost:8080/disable/${movie_id}`);
      console.log(movie_id);
      
      fetchData(); // Refresh list after successful disable
    } catch (err) {
      console.error("Disable failed:", err);
      console.log(movie_id);

      alert("Failed to disable movie.");
    } finally {
      setIsLoading(false); // Hide loading indicator
    }
  };

  const handleUpdate = async (movie) => {
    setIsLoading(true);
    try {
      await updateMovie(movie);
      setEditingMovie(null);
      fetchData(); // Refresh list after update
    } catch (error) {
      console.error("Update failed:", error);
      alert("Failed to update movie.");
    } finally {
      setIsLoading(false);
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      if (searchQuery.trim() === "") {
        fetchData(); // Load all movies if search is empty
      } else {
        const res = await searchMovie(searchQuery);
        setMovies(res.data);
      }
    } catch (error) {
      console.error("Search failed:", error);
      alert("Failed to search movie.");
    }
  };

  return (
    <div className="container mt-4">
      <h3>Movie List</h3>

      {/* Search Bar */}
      <form onSubmit={handleSearch} className="mb-3 d-flex">
        <input
          type="text"
          className="form-control me-2"
          placeholder="Search by title..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
        <button type="submit" className="btn btn-primary" >Search</button>
      </form>

      {/* Loading Indicator */}
      {isLoading && <div className="spinner-border" role="status"><span className="visually-hidden">Loading...</span></div>}

      <div className="d-flex flex-wrap">
        {movies.map((movie) => (
          <MovieCard
            key={movie.movie_id}
            movie={movie}
            onDelete={handleDisable}
            onUpdate={handleUpdate}
            isEditing={editingMovie?.movie_id === movie.movie_id}
            startEdit={() => setEditingMovie(movie)}
            cancelEdit={() => setEditingMovie(null)}
          />
        ))}
      </div>
    </div>
  );
};

export default MovieList;
