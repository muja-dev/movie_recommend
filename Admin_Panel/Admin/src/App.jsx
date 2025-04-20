import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import AddMovie from './components/AddMovie';
import MovieList from './components/MovieList';
import UserList from './components/UserList';
import Login from './components/Login';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('token'));

  const handleLogout = () => {
    localStorage.clear();
    setIsLoggedIn(false);
  };

  const handleLoginSuccess = () => {
    setIsLoggedIn(true);
  };

  if (!isLoggedIn) {
    return <Login onLogin={handleLoginSuccess} />;
  }

  return (
    <Router>
      <div className="container mt-3">
        <nav className="navbar navbar-expand-lg navbar-light bg-light mb-4">
          <Link className="navbar-brand" to="/">Movie App</Link>
          <div className="navbar-nav me-auto">
            <Link className="nav-link" to="/add">Add Movie</Link>
            <Link className="nav-link" to="/movies">View Movies</Link>
            <Link className="nav-link" to="/users">View Users</Link>
          </div>
          <button className="btn btn-outline-danger" onClick={handleLogout}>
            Logout
          </button>
        </nav>

        <Routes>
          <Route path="/" element={<div className="text-center"><h4>Welcome to Movie App</h4></div>} />
          <Route path="/add" element={<AddMovie />} />
          <Route path="/movies" element={<MovieList />} />
          <Route path="/users" element={<UserList />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
