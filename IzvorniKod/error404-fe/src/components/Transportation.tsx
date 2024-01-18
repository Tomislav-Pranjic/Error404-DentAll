import { useEffect, useState } from 'react';
import React from 'react';
import './Transportation.css';
import Driver from './Driver';
import { Base64 } from 'js-base64';


function Transportation(){

  const[drivers,setDrivers] = useState<any[]>([]);
  const[vehicles,setVehicles] = useState<any[]>([]);
  const[toggleAddDriver,setToggleAddDriver] = useState(false);

  const [editDriver, setEditDriver] = useState<number | null>(null);
  const [driverForm, setDriverForm] = useState({ name: '', surname: '',email: '',phoneNumber: '',
  vehicle: {
    registration: '',
    model: '',
    color: '',
    capacity: 0
  },workStartTime: '',workingDays: ''});

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
      .catch((error) => {
        console.error('Error fetching data:', error);
      })
  },[]);
  
  const handleAddDriverForm= () => {
    setToggleAddDriver(!toggleAddDriver);
  };

  function onChange(event: React.ChangeEvent<HTMLInputElement>) {
    const {name, value} = event.target;
    setDriverForm(oldForm => ({...oldForm, [name]: value}))
  }

  const handleToggleShowMore = (driverId: number) => {
    setEditDriver(prevId => (prevId === driverId ? null : driverId));
  };

  const handleEdit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    // const optionsVehicleGET = {
    //   method: 'GET',
    //   headers: new Headers({
    //       "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
    //       "Content-Type": "application/json",
    //         "Accept": "*/*",
    //       "Accept-Encoding": "gzip, deflate, br",
    //       "Connection": "keep-alive"
    //   }),
    //   body: ''
    // };
    // fetch('/api/vehicle')
    //   .then(data => data.json())
    //   .then(vehicles => setVehicles(vehicles))


    const dataVehicle = {
      registration: driverForm.vehicle.registration,
      model: driverForm.vehicle.model,
      color: driverForm.vehicle.color,
      capacity: driverForm.vehicle.capacity
    }


    const optionsVehiclePatch = {
      method: 'PATCH',
      headers: new Headers({
          "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
          "Content-Type": "application/json",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: JSON.stringify(dataVehicle)
    };
    fetch('api/vehicle', optionsVehiclePatch)
    .then(response => {
      if(response.ok){
        console.log('Uspjesno editano vozilo')
      }
    })

    const dataDriver= {
      firstName: driverForm.name, 
      lastName: driverForm.surname,
      email: driverForm.email,
      phoneNumber: driverForm.phoneNumber,
      vehicleReg: driverForm.vehicle.registration,
      workStartTime: driverForm.workStartTime,
      workingDays: driverForm.workingDays
    }
    const optionsDriver = {
      method: 'PATCH',
      headers: new Headers({
          "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
          "Content-Type": "application/json",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: JSON.stringify(dataDriver)
    };
    fetch('/api/driver',optionsDriver)
    .then(response => {
      if(response.ok){
        console.log('Uspjesno editan vozac')
      }
    })
  }





  const handleDelete = (id: number) => {
    console.log(`Delete button clicked for user with ID ${id}`);
  };

  const handleAdd = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()

    const dataVehicle = {
      registration: driverForm.vehicle.registration,
      model: driverForm.vehicle.model,
      color: driverForm.vehicle.color,
      capacity: driverForm.vehicle.capacity
    }
    const dataDriver= {
      firstName: driverForm.name, 
      lastName: driverForm.surname,
      email: driverForm.email,
      phoneNumber: driverForm.phoneNumber,
      vehicleReg: driverForm.vehicle.registration,
      workStartTime: driverForm.workStartTime,
      workingDays: driverForm.workingDays
    }

    const optionsVehiclePost = {
      method: 'POST',
      headers: new Headers({
          "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
          "Content-Type": "application/json",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: JSON.stringify(dataVehicle)
    };
    fetch('api/vehicle', optionsVehiclePost)
    .then(response => {
      if(response.ok){
        console.log('Uspjesno dodano vozilo')
      }
    })
    const optionsDriver = {
      method: 'PATCH',
      headers: new Headers({
          "Authorization": `Basic ${Base64.encode(`${storedUsername}:${storedPassword}`)}`,
          "Content-Type": "application/json",
            "Accept": "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          "Connection": "keep-alive"
      }),
      body: JSON.stringify(dataDriver)
    };
    fetch('/api/driver',optionsDriver)
    .then(response => {
      if(response.ok){
        console.log('Uspjesno dodan vozac')
      }
    })
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

                        <label className="form-label">
                          Phone Number:
                          <input className="form-control" type="text" name='phoneNumber' defaultValue={driver.phoneNumber} onChange={onChange}/>
                        </label>
                        
                        <br />
                        <label className="form-label">
                          Start of Work Time:
                          <input className="form-control" type="time" name='workStartTime' defaultValue={driver.workStartTime} onChange={onChange}/>
                        </label>
                        <label className="form-label">
                          Working Days:
                          <input className="form-control" type="text" name='workingDays' defaultValue={driver.workingDays} onChange={onChange}/>
                        </label>
                        <br />
                        <label className='form-label'>
                          Vehicle Registration:
                          <input className='form-control' type="text" name='vehicle.registration' defaultValue={driver.vehicle.registration} onChange={onChange}/>
                        </label>
                        <label className='form-label'>
                          Vehicle Model:
                          <input className='form-control' type="text" name='vehicle.model' defaultValue={driver.vehicle.model} onChange={onChange}/>
                        </label>
                        <br />
                        <label className='form-label'>
                          Vehicle Color:
                          <input className='form-control' type="text" name='vehicle.color' defaultValue={driver.vehicle.color} onChange={onChange}/>
                        </label>
                        <label className='form-label'>
                          Vehicle Capacity:
                          <input className='form-control' type="number" name='vehicle.capacity' defaultValue={driver.vehicle.capacity} onChange={onChange}/>
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
          {toggleAddDriver && (
            <form className="login-box" onSubmit={handleAdd}>
            <label className="form-label">
              First Name:
              <input
                className="form-control"
                type="text"
                name="name"
                placeholder='Name'
                onChange={onChange}
              />
            </label>
            <label className="form-label">
              Last Name:
              <input
                className="form-control"
                type="text"
                name='surname'
                placeholder='Surname'
                onChange={onChange}
              />
            </label>
            <br />
            <label className="form-label">
              Email:
              <input className="form-control" type="text" name='email' placeholder='namesurname@gmail.com' onChange={onChange}/>
            </label>

            <label className="form-label">
              Phone Number:
              <input className="form-control" type="text" name='phoneNumber' placeholder='0999876543' onChange={onChange}/>
            </label>
            
            <br />
            <label className="form-label">
              Start of Work Time:
              <input className="form-control" type="time" name='workStartTime' placeholder='HH:mm' onChange={onChange}/>
            </label>
            <label className="form-label">
              Working Days:
              <input className="form-control" type="text" name='workingDays' placeholder="NPUSCEB" onChange={onChange}/>
            </label>
            <br />
            <label className='form-label'>
              Vehicle Registration:
              <input className='form-control' type="text" name='vehicle.registration' placeholder='AZ1234AZ' onChange={onChange}/>
            </label>
            <label className='form-label'>
              Vehicle Model:
              <input className='form-control' type="text" name='vehicle.model' placeholder='Model' onChange={onChange}/>
            </label>
            <br />
            <label className='form-label'>
              Vehicle Color:
              <input className='form-control' type="text" name='vehicle.color' placeholder='Color' onChange={onChange}/>
            </label>
            <label className='form-label'>
              Vehicle Capacity:
              <input className='form-control' type="number" name='vehicle.capacity' placeholder='Capacity' onChange={onChange}/>
            </label>
            <br />
            <button type="submit" className="btn btn-primary me-5 px-3" >Save</button>
            <button type='button' className='btn btn-danger px-3' onClick={handleAddDriverForm}>Cancel</button>
          </form>
          )}

          {!toggleAddDriver && (
            <button type='button' className='btn btn-secondary ' onClick={handleAddDriverForm}>
            Add Driver
            </button>
          )}
        </div>
      </main>

      <footer className="footer-container">
        <p className="text-center text-muted">&copy; 2023 DentAll.</p>
      </footer>
    </div>
  );
};

export default Transportation;