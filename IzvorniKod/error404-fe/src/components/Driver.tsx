
function Driver(props: { driver: { driverId: any; name: any; surname: any; email: any; phoneNumber: any; vehicle: any; workStartTime: any; workingDays: any; }; }) {

    const {driverId,name,surname,email,phoneNumber,vehicle,workStartTime,workingDays} = props.driver;

    return (
        <div className="driver-card">
          <div>
            <h2>{name} {surname}</h2>
          </div>
        </div>
        
      );

}

export default Driver;

