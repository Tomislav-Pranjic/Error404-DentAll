import { useEffect, useState } from 'react';
import './Transportation.css';
import Driver from './Driver';
import { Base64 } from 'js-base64';


function Transportation(){

  const[drivers,setDrivers] = useState<any[]>([]);

  const storedUsername = localStorage.getItem('username');
  const storedPassword = localStorage.getItem('password');
  


  useEffect(() => {
    // event.preventDefault(); // da ne bi refreshalo


    const body = `username=${storedUsername}&password=${storedPassword}`;
    const options = {
      method: 'GET',
      headers: new Headers({
          "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
          "Content-Type": "application/json",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: body
    };
    

  },[]);
 



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
                
                {drivers.map(driver => (
                <Driver key={driver.driverId} driver={driver} />
              ))}

            </div>
          </main>
    
          <footer className="footer-container">
            <p className="text-center text-muted">&copy; 2023 DentAll.</p>
          </footer>
        </div>
      );
}

export default Transportation;