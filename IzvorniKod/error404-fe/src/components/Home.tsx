import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
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

        {/* ovdje treba dodati onaj glavni dio */}
        <p>home page</p>


        </div>
    </main>

    <footer className="footer-container">
      <p className="text-center text-muted">&copy; 2023 DentAll.</p>
    </footer>
  </div> 
    
  );
}

export default Home;
