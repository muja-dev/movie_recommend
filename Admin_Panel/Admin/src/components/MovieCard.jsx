import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

// Utility to extract YouTube video ID safely
const extractYouTubeID = (url) => {
  if (!url) return null;
  const match = url.match(
    /(?:youtu\.be\/|youtube\.com\/(?:watch\?v=|embed\/|v\/|shorts\/))([^?&\n]+)/i
  );
  return match ? match[1] : null;
};


const MovieCard = ({ movie, onDelete, onUpdate, isEditing, startEdit, cancelEdit }) => {
  const [formData, setFormData] = useState({ ...movie });
  const [showModal, setShowModal] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    const updatedData = {
      ...formData,
      actor: formData.actor.split(',').map(name => name.trim()),
      genre: formData.genre.split(',').map(name => name.trim()),
      rating: parseFloat(formData.rating),
    };
    onUpdate(updatedData);
  };

  const youtubeID = extractYouTubeID(movie.trailer_url || '');

  if (isEditing) {
    return (
      <div className="card m-3 p-2" style={{ width: '18rem' }}>
        <input className="form-control mb-2" name="title" value={formData.title} onChange={handleChange} />
        <input className="form-control mb-2" name="director" value={formData.director} onChange={handleChange} />
        <input className="form-control mb-2" name="rating" value={formData.rating} onChange={handleChange} />
        <input className="form-control mb-2" name="actor" value={formData.actor} onChange={handleChange} />
        <input className="form-control mb-2" name="poster_url" value={formData.poster_url} onChange={handleChange} />
        <input className="form-control mb-2" name="genre" value={formData.genre} onChange={handleChange} />
        <input className="form-control mb-2" name="trailer_url" value={formData.trailer_url || ''} onChange={handleChange} />
        <textarea className="form-control mb-2" name="description" value={formData.description} onChange={handleChange} />
        <div className="d-flex justify-content-between">
          <button className="btn btn-success btn-sm" onClick={handleSave}>Save</button>
          <button className="btn btn-secondary btn-sm" onClick={cancelEdit}>Cancel</button>
        </div>
      </div>
    );
  }

  return (
    <>
      <div className="card m-3 mt-5" style={{ width: '28rem', cursor: 'pointer' }} onClick={() => setShowModal(true)}>
        <img src={movie.poster_url} className="card-img-top" alt={movie.title} />
        <div className="card-body">
          <h5 className="card-title">{movie.title}</h5>
          <p className="card-text">{movie.description}</p>
          <p><strong>Director:</strong> {movie.director}</p>
          <p><strong>Rating:</strong> {movie.rating}</p>
          <div className="d-flex justify-content-between">
            <button className="btn btn-warning btn-sm" onClick={(e) => { e.stopPropagation(); startEdit(); }}>Update</button>
            <button className="btn btn-danger btn-sm" onClick={(e) => { e.stopPropagation(); onDelete(movie.movie_id); }}>Delete</button>
          </div>
        </div>
      </div>

      {/* Modal for full details */}
      <Modal show={showModal} onHide={() => setShowModal(false)} size="lg" centered>
        <Modal.Header closeButton>
          <Modal.Title>{movie.title}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <img src={movie.poster_url} alt={movie.title} style={{ width: '100%', marginBottom: '1rem' }} />
          <p><strong>Description:</strong> {movie.description}</p>
          <p><strong>Director:</strong> {movie.director}</p>
          <p><strong>Rating:</strong> {movie.rating}</p>
          <p><strong>Actors:</strong> {Array.isArray(movie.actor) ? movie.actor.join(', ') : movie.actor}</p>
          <p><strong>Genres:</strong> {Array.isArray(movie.genre) ? movie.genre.join(', ') : movie.genre}</p>
          {youtubeID ? (
            <div className="mt-3">
              <iframe
                width="100%"
                height="315"
                src={`${movie.yt_link}`}
                title="YouTube trailer"
                frameBorder="0"
                allowFullScreen
              />
            </div>
          ) : (
            <p className="text-muted">Trailer not available</p>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>Close</Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default MovieCard;
