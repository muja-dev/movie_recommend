import axios from "axios";

const API_BASE = "http://localhost:8080"; // Replace with your actual API

export const getMovies = () => axios.get(`${API_BASE}/viewAllMovies`);
export const addMovie = (movie) => axios.post(`${API_BASE}/addmovie`, movie);
export const deleteMovie = (id) => axios.delete(`http://localhost:8080/deleteById/${id}`);
export const updateMovie = (movie) => axios.put(`${API_BASE}/updatemovie`, movie);
export const getUsers = () => axios.get(`${API_BASE}/showAllUsers`);
export const searchMovie = (genre) =>
    axios.get(`${API_BASE}/searchByGenre`, {
      params: { genre }
    });

export const adminLogin = (email, password) =>
    axios.post(`${API_BASE}/adminlogin`, { email, password })
        .then(response => {
            const { token, userId } = response.data;
            localStorage.setItem('token', token);
            localStorage.setItem('userId', userId);
            return response.data;
        });
  