import { useEffect, useState } from 'react';
import './Transportation.css';
import Driver from './Driver';
import { Base64 } from 'js-base64';


function Transportation(){

  const[drivers,setDrivers] = useState<any[]>([]);
  const [showMore, setShowMore] = useState(false);

  const storedUsername = localStorage.getItem('username');
  const storedPassword = localStorage.getItem('password');
  


  useEffect(() => {
    // const body = `username=${storedUsername}&password=${storedPassword}`;
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
    fetch('/api/driver')
      .then(data => data.json())
      .then(drivers => setDrivers(drivers))

  },[]);


  const handleToggleShowMore = () => {
    setShowMore(!showMore);
  };

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
          <table className="table">
            <thead>
              <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              {drivers.map((driver) => (
                <tr key={driver.driverId}>
                  <td>{driver.name}</td>
                  <td>{driver.surname}</td>
                  <td>
                    <>
                      <button className="btn btn-outline-primary" onClick={handleToggleShowMore}>
                        {showMore ? 'Show Less' : 'Show More'}
                      </button>
                      
                      <button
                        className="btn btn-outline-primary"
                        onClick={() => handleEdit(driver.driverId)}
                      >
                        Edit
                      </button>

                      <button
                        className="btn btn-danger"
                        onClick={() => handleDelete(driver.driverId)}
                      >
                        Delete
                      </button>
                    </>
                  </td>
                </tr>
                // <tr></tr> ---> ovo kada bude show more
              ))}
            </tbody>
          </table>
        </div>
      </main>

      <footer className="footer-container">
        <p className="text-center text-muted">&copy; 2023 DentAll.</p>
      </footer>
    </div>
  );
}

export default Transportation;


// {showMore && (
//   <div>
//     <p>Email: {email}</p>
//     <p>Phone Number: {phoneNumber}</p>
//     {/* <p>Vehicle Registration: {vehicleReg}</p> */}
//     <p>Work Start Time: {workStartTime}</p>
//     <p>Working Days: {workingDays}</p>
//   </div>
// )}