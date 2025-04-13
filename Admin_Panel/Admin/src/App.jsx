import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AddMovie from './components/AddMovie';
import MovieList from './components/MovieList';
import UserList from './components/UserList';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => (
  <Router>
    <div className="container mt-3">
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <Link className="navbar-brand" to="/">Movie App</Link>
        <div className="navbar-nav">
          <Link className="nav-link" to="/add">Add Movie</Link>
          <Link className="nav-link" to="/movies">View Movies</Link>
          <Link className="nav-link" to="/users">View Users</Link>
        </div>
      </nav>
      <Routes>
        <Route path="/" element={<MovieList />} />
        <Route path="/add" element={<AddMovie />} />
        <Route path="/movies" element={<MovieList />} />
        <Route path="/users" element={<UserList />} />
      </Routes>
    </div>
  </Router>
);

export default App;
