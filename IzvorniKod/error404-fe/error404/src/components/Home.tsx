import { useState } from "react";
import "./Home.css";

function Home() {
  const initialData = [
    { id: 1, ime: "Ana", prezime: "Anić" },
    { id: 2, ime: "Marko", prezime: "Marković" },
    { id: 3, ime: "Ivo", prezime: "Ivić" },
    { id: 4, ime: "Pero", prezime: "Perić" },
  ];

  const [data] = useState(initialData);

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
            <a className="nav-link" href="/home">
              Accommodation
            </a>
            <a className="nav-link" href="/home">
              Transportation
            </a>
            <a className="nav-link" href="/home">
              Users
            </a>
            <button
              type="button"
              className="btn btn-dark"
              onClick={() => console.log("Logout button clicked")}
            >
              Logout
            </button>
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
                <th></th>
              </tr>
            </thead>
            <tbody>
              {data.map((item) => (
                <tr key={item.id}>
                  <td>{item.id}</td>
                  <td>{item.ime}</td>
                  <td>{item.prezime}</td>
                  <td>
                    <>
                      <button
                        className="btn btn-outline-primary"
                        onClick={() => handleEdit(item.id)}
                      >
                        Edit
                      </button>

                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(item.id)}
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

export default Home;
