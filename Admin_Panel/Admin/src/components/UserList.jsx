import React, { useEffect, useState } from 'react';
import { getUsers } from '../api';

const UserList = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers().then(res => setUsers(res.data));
   
  }, []);

  return (
    <div className="container mt-4">
      <h3>Users</h3>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>User ID</th><th>Username</th><th>Email</th><th>Created At</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.userId}>
              <td>{user.userId}</td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>{user.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default UserList;
