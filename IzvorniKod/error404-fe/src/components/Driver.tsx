
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
        </div>
        
      );

}

export default Driver;

