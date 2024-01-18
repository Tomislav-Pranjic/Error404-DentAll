
import { useState } from 'react';


function Driver(props: { driver: { driverId: any; name: any; surname: any; email: any; phoneNumber: any; vehicle: any; workStartTime: any; workingDays: any; }; }) {
    const [showMore, setShowMore] = useState(false);
    const {driverId,name,surname,email,phoneNumber,vehicle,workStartTime,workingDays} = props.driver;

    const handleToggleShowMore = () => {
        setShowMore(!showMore);
    };

    return (
        <div className="driver-card">
          <div>
            <h2>{name} {surname}</h2>
            {showMore && (
              <div>
                <p>Email: {email}</p>
                <p>Phone Number: {phoneNumber}</p>
                {/* <p>Vehicle Registration: {vehicleReg}</p> */}
                <p>Work Start Time: {workStartTime}</p>
                <p>Working Days: {workingDays}</p>
              </div>
            )}
          </div>
          <button onClick={handleToggleShowMore}>
            {showMore ? 'Show Less' : 'Show More'}
          </button>
        </div>
        
      );

}

export default Driver;


// function Driver(props) {
//     const {firstName,lastName,email,phoneNumber,vehicleReg,workStartTime,workingDays} = props.driver;
  
//     firstName: "",
//  lastName: "",
//  email: "",
//  phoneNumber: "",
//  vehicleReg: "AZ1234AZ" or "AZ123AZ",
//  workStartTime: "HH:mm",
//  workingDays: "NPUSCEB" #Staviti inicijale onih dana kada će vozač raditi
 
//     return (
//       <p>{firstName}</p>
//     );
//   }
  
//   export default Driver;

// Driver.tsx