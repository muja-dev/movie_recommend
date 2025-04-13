import React, { useState } from 'react';
import { addMovie } from '../api';

const AddMovie = () => {
  const [form, setForm] = useState({
    title: '',
    release_year: '',
    director: '',
    rating: '',
    description: '',
    poster_url: '',
    yt_link: '',
    actor: [''],
    genre: ['']
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleArrayChange = (field, index, value) => {
    const updated = [...form[field]];
    updated[index] = value;
    setForm({ ...form, [field]: updated });
  };

  const addArrayField = (field) => {
    setForm({ ...form, [field]: [...form[field], ''] });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const movie = {
      ...form,
      actor: form.actor.filter((a) => a.trim() !== ''),
      genre: form.genre.filter((g) => g.trim() !== '')
    };

    try {
      await addMovie(movie);
      alert('Movie Added!');
      setForm({
        title: '',
        release_year: '',
        director: '',
        rating: '',
        description: '',
        poster_url: '',
        yt_link: '',
        actor: [''],
        genre: ['']
      });
    } catch (error) {
      console.error('Error adding movie:', error);
      alert('Failed to add movie.');
    }
  };

  return (
    <div className="container mt-4">
      <h3>Add Movie</h3>
      <form onSubmit={handleSubmit}>
        {["title", "release_year", "director", "rating", "description", "poster_url", "yt_link"].map(field => (
          <div className="mb-3" key={field}>
            <label className="form-label">{field.replace("_", " ").toUpperCase()}</label>
            <input
              type="text"
              className="form-control"
              name={field}
              value={form[field]}
              onChange={handleChange}
              required={["title", "release_year", "director"].includes(field)}
            />
          </div>
        ))}

        <div className="mb-3">
          <label className="form-label">ACTORS</label>
          {form.actor.map((actor, index) => (
            <input
              key={index}
              type="text"
              className="form-control mb-2"
              value={actor}
              onChange={(e) => handleArrayChange('actor', index, e.target.value)}
            />
          ))}
          <button type="button" className="btn btn-sm btn-secondary" onClick={() => addArrayField('actor')}>
            + Add Actor
          </button>
        </div>

        <div className="mb-3">
          <label className="form-label">GENRES</label>
          {form.genre.map((genre, index) => (
            <input
              key={index}
              type="text"
              className="form-control mb-2"
              value={genre}
              onChange={(e) => handleArrayChange('genre', index, e.target.value)}
            />
          ))}
          <button type="button" className="btn btn-sm btn-secondary" onClick={() => addArrayField('genre')}>
            + Add Genre
          </button>
        </div>

        <button className="btn btn-primary">Add Movie</button>
      </form>
    </div>
  );
};

export default AddMovie;
