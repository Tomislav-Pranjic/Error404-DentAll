import React, { useEffect,useState } from 'react';
import "./Users.css";
import User from './User';
import UserForm from './UserForm';
import { Base64 } from 'js-base64';

function Users() {
  
  const[users,setUsers] = useState<any[]>([]);
  

  const storedUsername = localStorage.getItem('username');
  const storedPassword = localStorage.getItem('password');

  React.useEffect(() => {
      const options = {
          method: 'GET',
          headers: new Headers({
              "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
              "Content-Type": "application/json",
                "Accept": "*/*",
              "Accept-Encoding": "gzip, deflate, br",
              "Connection": "keep-alive"
          }),
          body: ''
      };
      fetch('/users')
      .then(data => data.json())
      .then(users => setUsers(users))
  }, []);
  
  const handleEdit = (id: number) => {
    console.log(`Edit button clicked for user with ID ${id}`);
  };

  const handleDelete = (id: number) => {
    console.log(`Delete button clicked for user with ID ${id}`);
  };

  const handleAdd = () => {
    console.log("Add button clicked");
  };

  return (
    <div className="home-container">
      <header>
        <nav className="navbar bg-body-tertiary">
          <div className="container-fluid">
            <a className="navbar-brand">DentAll</a>
            <a className="nav-link" href="/accommodation">
              Accommodation
            </a>
            <a className="nav-link" href="/transportation">
              Transportation
            </a>
            <a className="nav-link" href="/users">
              Users
            </a>
            <a className="btn btn-dark" href="/" role="button">Logout</a>
          </div>
        </nav>
      </header>
      <main>
        <div className="main-container">
          <br />
          <h2>User data</h2>
          <br />
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Phone</th>
                <th>Date Of Birth</th>
                <th>Prefered Accomodation</th>
              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.name}</td>
                  <td>{user.lastname}</td>
                  <td>{user.phone}</td>
                  <td>{user.dateOfBirth}</td>
                  <td>{user.accTypePrefId}</td>
                  <td>
                    <>
                      <button
                        className="btn btn-outline-primary"
                        onClick={() => handleEdit(user.id)}
                      >
                        Edit
                      </button>

                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(user.id)}
                      >
                        Delete
                      </button>
                    </>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <br />
          <button className="btn btn-secondary" onClick={handleAdd}>
            Add Data
          </button>
        </div>

      </main>

      <footer className="footer-container">
        <p className="text-center text-muted">&copy; 2023 DentAll.</p>
      </footer>
    </div>

  );
}

export default Users;
