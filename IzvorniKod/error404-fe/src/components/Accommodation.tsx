import { useEffect, useState } from "react";
//import { useNavigate } from 'react-router-dom';
import { Base64 } from "js-base64";
import "./Accommodation.css";

function Accommodation() {
  const [admins, setAdmins] = useState<any[]>([]);

  const [adminFormOpen, setAdminFormOpen] = useState(false);

  const storedUsername = localStorage.getItem("username");
  const storedPassword = localStorage.getItem("password");

  const [accommodations, setAccommodations] = useState<any[]>([]);
  const [accommodationFormOpen, setAccommodationFormOpen] = useState(false);

  useEffect(() => {
    //nesto ne valja jer vraca error 401 unauthorised

    //Dohvat podataka za admine
    const adminOptions = {
      method: "GET",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: "",
    };
    fetch("/api/admin")
      .then((data) => data.json())
      .then((admin) => setAdmins(admin));

    // Dohvati podatke o smještajima
    const accommodationOptions = {
      method: "GET",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: "",
    };
    fetch("/api/accommodation")
      .then((data) => data.json())
      .then((accommodation) => setAccommodations(accommodation));
  }, []);

  const [adminData, setAdminData] = useState({
    username: "",
    password: "",
    roleIds: "",
  });

  const handleNewAdminClick = () => {
    setAdminFormOpen(true);
  };

  const handleAdminFormClose = () => {
    setAdminFormOpen(false);
    setAdminData({
      username: "",
      password: "",
      roleIds: "",
    });
  };

  const handleAdminChange = (e: any) => {
    const { name, value } = e.target;
    setAdminData({
      ...adminData,
      [name]: value,
    });
  };

  function adminFormIsValid() {
    const { username, password, roleIds } = adminData;
    return username.length > 0 && password.length > 0 && roleIds.length > 0;
  }

  const handleAdminSubmit = (e: any) => {
    e.preventDefault();
    // Pretvori string s role ID-ovima u niz brojeva
    const roleIdsArray = adminData.roleIds
      .split(",")
      .map((roleId) => Number(roleId));

    const data = {
      userName: adminData.username,
      password: adminData.password,
      roleIds: roleIdsArray,
    };
    const options = {
      method: "POST",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: JSON.stringify(data),
    };
    handleAdminFormClose();
    console.log("Podaci o adminu:", data);
    return fetch("api/admins", options);
  };

  const getRoleNames = (roleIdsString: any) => {
    const roleIds = roleIdsString.split(",").map(Number);
    const roleNames = [];

    if (roleIds.includes(1)) {
      roleNames.push("Accommodation");
    }
    if (roleIds.includes(2)) {
      roleNames.push("Transport");
    }
    if (roleIds.includes(3)) {
      roleNames.push("Users");
    }

    return roleNames.join(" ");
  };
  //**Brisanje admina */
  const handleDeleteAdmin = async (adminId: any) => {
    const response = await fetch(`/api/admins/${adminId}`, {
      method: "DELETE",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
    });
    window.location.reload();
  };
  //*****EDIT ADMINA */

  const handleEditAdmin = (e: any) => {};

  //************SMJEŠTAJ*************** */

  const [accommodationData, setAccommodationData] = useState({
    typeId: 0,
    type: {
      typeName: "",
      typeSize: 32,
    },
    stars: 1,
    addressId: 0,
    address: {
      city: "",
      street: "",
      number: 1,
    },
    ownership: "True",
    availableUntil: "",
    noOfBeds: 1,
  });

  const handleAccommodationFormClose = () => {
    setAccommodationFormOpen(false);
    setAccommodationData({
      typeId: 0,
      type: {
        typeName: "",
        typeSize: 32,
      },
      stars: 1,
      addressId: 0,
      address: {
        city: "",
        street: "",
        number: 1,
      },
      ownership: "True",
      availableUntil: "",
      noOfBeds: 1,
    });
  };

  const handleAccommodationChange = (e: any) => {
    const { name, value } = e.target;

    if (name.startsWith("address.")) {
      const addressField = name.split(".")[1];
      setAccommodationData((prevData) => ({
        ...prevData,
        address: {
          ...prevData.address,
          [addressField]: value,
        },
      }));
    } else if (name.startsWith("type.")) {
      const typeField = name.split(".")[1];
      setAccommodationData((oldData) => ({
        ...oldData,
        type: {
          ...oldData.type,
          [typeField]: value,
        },
      }));
    } else {
      setAccommodationData({
        ...accommodationData,
        [name]: value,
      });
    }
  };
  function accommodationFormIsValid() {
    const { type, stars, address, ownership, availableUntil, noOfBeds } =
      accommodationData;

    const isOwnershipFalse = ownership === "False";

    return (
      type.typeName.length > 0 &&
      stars >= 1 &&
      address.city.length > 0 &&
      address.street.length > 0 &&
      address.number >= 1 &&
      (ownership === "True" || ownership === "False") &&
      (isOwnershipFalse ? availableUntil.length > 0 : true) &&
      noOfBeds >= 1
    );
  }

  //dodavanje smještaja
  const handleAccommodationSubmit = async (e: any) => {
    e.preventDefault();

    // Dodaj tip smještaja
    const typeData = {
      typeName: accommodationData.type.typeName,
      typeSize: accommodationData.type.typeSize,
    };

    const typeOptions = {
      method: "POST",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: JSON.stringify(typeData),
    };

    const typeResponse = await fetch("api/accommodation/type", typeOptions);
    const newType = await typeResponse.json();

    // Dodaj adresu
    const addressData = {
      city: accommodationData.address.city,
      street: accommodationData.address.street,
      number: accommodationData.address.number,
    };

    const addressOptions = {
      method: "POST",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: JSON.stringify(addressData),
    };

    const addressResponse = await fetch("api/address", addressOptions);
    const newAddress = await addressResponse.json();
    const [typeResult, addressResult] = await Promise.all([
      typeResponse,
      addressResponse,
    ]);

    // Sada možete poslati podatke o smještaju
    const accData = {
      typeId: newType.typeId,
      stars: accommodationData.stars,
      addressId: newAddress.addressId,
      ownership: accommodationData.ownership,
      availableUntil: accommodationData.availableUntil,
      noOfBeds: accommodationData.noOfBeds,
    };

    const AccommodationOptions = {
      method: "POST",
      headers: new Headers({
        Authorization: `Basic ${Base64.encode(
          `${storedUsername}:${storedPassword}`
        )}`,
        "Content-Type": "application/json",
        Accept: "*/*",
        "Accept-Encoding": "gzip, deflate, br",
        Connection: "keep-alive",
      }),
      body: JSON.stringify(accData),
    };

    handleAccommodationFormClose();
  };

  const handleEditAccommodation = (accommodation: any) => {
    // Implementirajte logiku za uređivanje smještaja
    // Možete koristiti sličan pristup kao i za admina
  };

  const handleDeleteAccommodation = async (accommodationId: any) => {
    try {
      const response = await fetch(`/api/accommodation/${accommodationId}`, {
        method: "DELETE",
        headers: new Headers({
          Authorization: `Basic ${Base64.encode(
            `${storedUsername}:${storedPassword}`
          )}`,
          "Content-Type": "application/json",
          Accept: "*/*",
          "Accept-Encoding": "gzip, deflate, br",
          Connection: "keep-alive",
        }),
      });
      window.location.reload();
    } catch (error) {
      console.error("Error deleting accommodation:", error);
    }
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
            <a className="btn btn-dark" href="/" role="button">
              Logout
            </a>
          </div>
        </nav>
      </header>
      <main>
        <div className="main-container">
          <div className="admin-container">
            {/* Lista Admina */}
            <div className="admin-list">
              <h2>List of Admins</h2>
              <table className="table">
                <thead>
                  <tr>
                    <th>Username</th>
                    <th>Role</th>
                  </tr>
                </thead>

                <tbody>
                  {admins.map((admin) => (
                    <tr key={admin.username}>
                      <td>{admin.username}</td>
                      <td>{getRoleNames(admin.roleIds)}</td>
                      <td>
                        <button
                          className="btn btn-success"
                          onClick={() => handleEditAdmin(admin)}
                        >
                          Edit
                        </button>
                        <button
                          className="btn btn-danger"
                          onClick={() => handleDeleteAdmin(admin.adminId)}
                        >
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>

            {/* Forma za dodavanje novih admina */}
            {!adminFormOpen && (
              <button className="btn btn-primary" onClick={handleNewAdminClick}>
                Add New Admin
              </button>
            )}
            {adminFormOpen && (
              <form onSubmit={handleAdminSubmit} className="admin-form ">
                <div className="form-row">
                  <div className="form-group col-md">
                    <label>
                      Username:
                      <input
                        type="text"
                        className="form-control"
                        name="username"
                        onChange={handleAdminChange}
                      />
                    </label>
                  </div>
                  <div className="form-group col-md">
                    <label>
                      Password:
                      <input
                        type="password"
                        className="form-control"
                        name="password"
                        onChange={handleAdminChange}
                      />
                    </label>
                  </div>
                  <div className="form-group col-md">
                    <label>
                      *Role Ids:
                      <input
                        type="text"
                        className="form-control"
                        name="roleIds"
                        onChange={handleAdminChange}
                      />
                    </label>
                  </div>
                </div>
                <br />
                <p>
                  *1-accommodation, 2-transportation , 3-users (separated by
                  commma)
                </p>
                <button
                  type="submit"
                  disabled={!adminFormIsValid()}
                  className="btn btn-primary"
                >
                  Add admin
                </button>
                <button
                  className="btn btn-secondary"
                  type="button"
                  onClick={handleAdminFormClose}
                >
                  Cancel
                </button>
              </form>
            )}

            <br />
          </div>
          <br />
          <br />
          <br />

          {/* SMJEŠTAJ */}
          {/* Lista Smještaja */}
          <div className="accommodation-list">
            <h2>List of Accommodations</h2>
            <table className="table">
              <thead>
                <tr>
                  <th>Type</th>
                  <th>Stars</th>
                  <th>City</th>
                  <th>Street Number</th>
                  <th>Number</th>
                  <th>Ownership</th>
                  <th>No. of Beds</th>
                </tr>
              </thead>
              <tbody>
                {accommodations.map((accommodation) => (
                  <tr key={accommodation.typeId}>
                    <td>{accommodation.type.typeName}</td>
                    <td>{accommodation.stars}</td>
                    <td>{accommodation.address.city}</td>
                    <td>{accommodation.address.street}</td>
                    <td>{accommodation.address.number}</td>
                    <td>{accommodation.ownership}</td>
                    <td>{accommodation.noOfBeds}</td>
                    <td>
                      <button
                        className="btn btn-success"
                        onClick={() => handleEditAccommodation(accommodation)}
                      >
                        Edit
                      </button>
                    </td>
                    <td>
                      <button
                        className="btn btn-danger"
                        onClick={() =>
                          handleDeleteAccommodation(accommodation.typeId)
                        }
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {/* Forma za dodavanje novog smještaja */}
          {!accommodationFormOpen && (
            <button
              className="btn btn-primary"
              onClick={() => setAccommodationFormOpen(true)}
            >
              Add New Accommodation
            </button>
          )}
          {accommodationFormOpen && (
            <form
              onSubmit={handleAccommodationSubmit}
              className="accommodation-form"
            >
              <div className="form-row">
                <div className="form-group col-md">
                  <label>
                    Type Name:
                    <input
                      type="text"
                      className="form-control"
                      name="type.typeName"
                      onChange={handleAccommodationChange}
                    />
                  </label>
                </div>
                <div className="form-group col-md">
                  <label>
                    Stars:
                    <select
                      className="form-control"
                      name="stars"
                      onChange={handleAccommodationChange}
                    >
                      {[1, 2, 3, 4, 5].map((star) => (
                        <option key={star} value={star}>
                          {star}
                        </option>
                      ))}
                    </select>
                  </label>
                </div>
                <div className="form-group col-md">
                  <label>
                    City:
                    <input
                      type="text"
                      className="form-control"
                      name="address.city"
                      onChange={handleAccommodationChange}
                    />
                  </label>
                </div>
                <div className="form-group col-md">
                  <label>
                    Street:
                    <input
                      type="text"
                      className="form-control"
                      name="address.street"
                      onChange={handleAccommodationChange}
                    />
                  </label>
                </div>
                <div className="form-group col-md">
                  <label>
                    Street Number:
                    <input
                      type="number"
                      className="form-control"
                      name="address.number"
                      onChange={handleAccommodationChange}
                    />
                  </label>
                </div>
                <div className="form-group col-md">
                  <label>
                    Ownership:
                    <select
                      className="form-control"
                      name="ownership"
                      onChange={handleAccommodationChange}
                    >
                      <option value="True">Owned</option>
                      <option value="False">Rented</option>
                    </select>
                  </label>
                </div>
                {accommodationData.ownership == "False" && (
                  <div className="form-group col-md">
                    <label>
                      Available Until:
                      <input
                        type="date"
                        className="form-control"
                        name="availableUntil"
                        value={accommodationData.availableUntil}
                        onChange={handleAccommodationChange}
                      />
                    </label>
                  </div>
                )}

                <div className="form-group col-md">
                  <label>
                    No. of Beds:
                    <input
                      type="number"
                      className="form-control"
                      name="noOfBeds"
                      onChange={handleAccommodationChange}
                    />
                  </label>
                </div>
              </div>
              <br />
              <button
                type="submit"
                disabled={!accommodationFormIsValid()}
                className="btn btn-primary"
              >
                Add Accommodation
              </button>

              <button
                className="btn btn-secondary"
                type="button"
                onClick={handleAccommodationFormClose}
              >
                Cancel
              </button>
            </form>
          )}
        </div>
      </main>

      <footer className="footer-container">
        <p className="text-center text-muted">&copy; 2023 DentAll.</p>
      </footer>
    </div>
  );
}

export default Accommodation;
