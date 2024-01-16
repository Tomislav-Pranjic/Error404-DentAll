# AdminController Endpoints

- **List Admins:**
  - Endpoint: `GET /admins`
  - Description: Retrieve a list of all admins.

- **Get Admin by ID:**
  - Endpoint: `GET /admins/{id}`
  - Description: Retrieve details of a specific admin by ID.

- **Create Admin:**
  - Endpoint: `POST /admins`
  - Description: Create a new admin.
  - Request Body: 
  ```
  {
    userName: "",
    password: "",
    roleIds: [1, 2, 3 ...]
  }
  ```

- **Update Admin by ID:**
  - Endpoint: `PATCH /admins/{id}`
  - Description: Update details of a specific admin by ID.
  - Request Body: 
  ```
  {
    userName: "",
    password: "",
    roleIds: [1, 2, 3 ...]
  }
  ```

- **List Admin Roles:**
  - Endpoint: `GET /admins/roles`
  - Description: Retrieve a list of all admin roles.

# AccommodationController Endpoints

- **List Accommodations:**
  - Endpoint: `GET /accommodation`
  - Description: Retrieve a list of all accommodations.

- **Get Accommodation by ID:**
  - Endpoint: `GET /accommodation/{id}`
  - Description: Retrieve details of a specific accommodation by ID.

- **Create Accommodation:**
  - Endpoint: `POST /accommodation`
  - Description: Create a new accommodation.
  - Request Body: 
    ``` 
    { 
    typeId: 0,
    stars: 1-5,
    addressId: 0,
    ownership: "True" or "False",
    availableUntil: if ownership False: "yyyy-MM-dd", else "",
    noOfBeds: 1
    }
    ```

- **Update Accommodation by ID:**
  - Endpoint: `PATCH /accommodation/{id}`
  - Description: Update details of a specific accommodation by ID.
  - Request Body: 
  ``` 
    { 
    typeId: 0,
    stars: 1-5,
    addressId: 0,
    ownership: "True" or "False",
    availableUntil: if ownership False: "yyyy-MM-dd", else "",
    noOfBeds: 1
    }
    ```

- **List Accommodation Types:**
  - Endpoint: `GET /accommodation/type`
  - Description: Retrieve a list of all accommodation types.

- **Get Accommodation Type by ID:**
  - Endpoint: `GET /accommodation/type/{id}`
  - Description: Retrieve details of a specific accommodation type by ID.

- **Create Accommodation Type:**
  - Endpoint: `POST /accommodation/type`
  - Description: Create a new accommodation type.
  - Request Body: 
  ``` 
    { 
    typeName: "",
    typeSize: 32
    }
    ```

- **Update Accommodation Type by ID:**
  - Endpoint: `PATCH /accommodation/type/{id}`
  - Description: Update details of a specific accommodation type by ID.
  - Request Body: 
  ``` 
    { 
    typeName: "",
    typeSize: 32
    }
    ```

# AddressController Endpoints

- **List Addresses:**
  - Endpoint: `GET /address`
  - Description: Retrieve a list of all addresses.

- **Get Address by ID:**
  - Endpoint: `GET /address/{id}`
  - Description: Retrieve details of a specific address by ID.

- **Create Address:**
  - Endpoint: `POST /address`
  - Description: Create a new address.
  - Request Body: 
  ``` 
    { 
    city: "",
    street: "",
    number: 1
    }
    ```

# DriverController Endpoints

- **List Drivers:**
  - Endpoint: `GET /driver`
  - Description: Retrieve a list of all drivers.

- **Create Driver:**
  - Endpoint: `POST /driver`
  - Description: Create a new driver.
  - Request Body: 
   ``` 
    { 
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    vehicleReg: "AZ1234AZ" or "AZ123AZ",
    workStartTime: "HH:mm",
    workingDays: "NPUSCEB" #Staviti inicijale onih dana kada će vozač raditi
    }
    ```

# MedUserController Endpoints

- **List Users:**
  - Endpoint: `GET /user`
  - Description: Retrieve a list of all medical users.

- **Create Medical User:**
  - Endpoint: `POST /user`
  - Description: Create a new medical user.
  - Request Body: 
  ``` 
    { 
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: "",
    accTypePrefId: 1,
    dateOfBirth: "yyyy-MM-dd"
    }
    ```

# VehicleController Endpoints

- **List Vehicles:**
  - Endpoint: `GET /vehicle`
  - Description: Retrieve a list of all vehicles.

- **Create Vehicle:**
  - Endpoint: `POST /vehicle`
  - Description: Create a new vehicle.
  - Request Body: 
  ``` 
    { 
    registration: "AZ1234AZ" or "AZ123AZ",
    model: "",
    color: "",
    capacity: 1,
    }
    ```


