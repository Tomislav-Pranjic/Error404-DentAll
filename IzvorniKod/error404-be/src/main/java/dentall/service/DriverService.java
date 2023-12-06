package dentall.service;

import dentall.domain.Driver;

import java.util.List;

public interface DriverService {

    List<Driver> listAll();

    Driver createDriver(String name, String surname, String email, String phoneNumber, String vehicleRegistration, String workStartTime, String workingDays);

}
