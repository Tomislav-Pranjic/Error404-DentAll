package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.DriverRepository;
import dentall.domain.Driver;
import dentall.domain.Vehicle;
import dentall.service.DriverService;
import dentall.service.VehicleService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceJpa implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleService vehicleService;

    @Override
    public List<Driver> listAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver createDriver(String name, String surname, String email, String phoneNumber, String vehicleRegistration, String workStartTime, String workingDays) {
        Assert.hasText(name, "Name must be set.");
        Assert.isTrue(name.length() <= 16, "Name must not be longer than 16 characters.");

        Assert.hasText(surname, "Surname must be set.");
        Assert.isTrue(surname.length() <= 16, "Surname must not be longer than 16 characters.");

        Assert.hasText(email, "Email must be set.");
        Assert.isTrue(email.length() <= 64, "Email must not be longer than 64 characters.");
        Assert.isTrue(email.matches(Error404BeApplication.EMAIL_FORMAT), "Email must be in valid format.");

        Assert.hasText(phoneNumber, "Phone number must be set.");
        phoneNumber = phoneNumber.replaceAll(" ", "");
        Assert.isTrue(phoneNumber.matches(Error404BeApplication.PHONE_NUMBER_FORMAT), "Phone number must be in valid format.");

        Assert.isTrue(vehicleRegistration.matches(Error404BeApplication.REGISTRATION_FORMAT), "Vehicle registration must be in valid format.");
        Optional<Vehicle> vehicle = vehicleService.findByRegistration(vehicleRegistration);
        if (vehicle.isEmpty()) {
            throw new ItemNotFoundException("Vehicle with registration '" + vehicleRegistration + "' does not exist.");
        }

        Assert.hasText(workStartTime, "Work start time must be set.");
        Assert.isTrue(workStartTime.matches(Error404BeApplication.TIME_FORMAT), "Work start time must be in valid format(HH:mm).");
        Time time = Time.valueOf(workStartTime + ":00");

        Assert.hasText(workingDays, "Working days must be set.");
        Assert.isTrue(workingDays.matches(Error404BeApplication.WORKING_DAYS_FORMAT), "Working days must be in valid format(NPUSCEB).");

        Driver driver = new Driver(name, surname, email, phoneNumber, vehicle.get(), time, workingDays);
        return driverRepository.save(driver);
    }
}