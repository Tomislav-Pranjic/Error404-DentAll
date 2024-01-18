import { useEffect, useState } from 'react';
import React from 'react';
import './Transportation.css';
import Driver from './Driver';
import { Base64 } from 'js-base64';


function Transportation(){

  const[drivers,setDrivers] = useState<any[]>([]);
  const [editDriver, setEditDriver] = useState<number | null>(null);
  const [driverForm, setDriverForm] = useState({ name: '', surname: '',email: '',phoneNumber: '',workStartTime: '',workingDays: ''});

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

  function onChange(event: React.ChangeEvent<HTMLInputElement>) {
    const {name, value} = event.target;
    setDriverForm(oldForm => ({...oldForm, [name]: value}))
  }


  const handleToggleShowMore = (driverId: number) => {
    setEditDriver(prevId => (prevId === driverId ? null : driverId));
  };

  const handleEdit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    console.log("edit")
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
                <React.Fragment key={driver.driverId}>
                  <tr>
                    <td>{driver.name}</td>
                    <td>{driver.surname}</td>
                    <td>
                      <>
                        <button
                          className="btn btn-outline-primary"
                          onClick={() => handleToggleShowMore(driver.driverId)}
                        >
                          {editDriver === driver.driverId ? 'Cancel' : 'Edit'}
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
                  {editDriver === driver.driverId && (
                    <tr key={`form_${driver.driverId}`}>
                    <td colSpan={3}>
                      <form className="login-box" onSubmit={handleEdit}>
                        <label className="form-label">
                          First Name:
                          <input
                            className="form-control"
                            type="text"
                            name="name"
                            defaultValue={driver.name}
                            onChange={onChange}
                          />
                        </label>
                        <br />
                        <label className="form-label">
                          Last Name:
                          <input
                            className="form-control"
                            type="text"
                            name='surname'
                            defaultValue={driver.surname}
                            onChange={onChange}
                          />
                        </label>
                        <br />
                        <label className="form-label">
                          Email:
                          <input className="form-control" type="text" name='email' defaultValue={driver.email} onChange={onChange}/>
                        </label>
                        <br />
                        <label className="form-label">
                          Phone Number:
                          <input className="form-control" type="text" name='phoneNumber' defaultValue={driver.phoneNumber} onChange={onChange}/>
                        </label>
                        <br />
                        <label className="form-label">
                          Start of Work Time:
                          <input className="form-control" type="text" name='workStartTime' defaultValue={driver.workStartTime} onChange={onChange}/>
                        </label>
                        <label className="form-label">
                          Working Days:
                          <input className="form-control" type="text" name='workingDays' defaultValue={driver.workingDays} onChange={onChange}/>
                        </label>
                        <br />
                        <button type="submit" className="btn btn-primary">Save</button>
                      </form>
                    </td>
                  </tr>
                  )}
                </React.Fragment>
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