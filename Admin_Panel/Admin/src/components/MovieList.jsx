import React, { useEffect, useState } from 'react';
import { getMovies, deleteMovie, updateMovie, searchMovie } from '../api';
import MovieCard from './MovieCard';


const MovieList = () => {
  const [movies, setMovies] = useState([]);
  const [editingMovie, setEditingMovie] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");

  const fetchData = async () => {
    const res = await getMovies();
    setMovies(res.data);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = async (movie_id) => {
    try {
      await deleteMovie(movie_id);
      fetchData();
    } catch (error) {
      console.error("Delete failed:", error);
      alert("Failed to delete movie.");
    }
  };

  const handleUpdate = async (movie) => {
    try {
      await updateMovie(movie);
      setEditingMovie(null);
      fetchData();
    } catch (error) {
      console.error("Update failed:", error);
      alert("Failed to update movie.");
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    try {
      if (searchQuery.trim() === "") {
        fetchData(); // If empty, load all
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
        <button type="submit" className="btn btn-primary">Search</button>
      </form>

      <div className="d-flex flex-wrap">
        {movies.map((movie) => (
          <MovieCard
            key={movie.movie_id}
            movie={movie}
            onDelete={handleDelete}
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
