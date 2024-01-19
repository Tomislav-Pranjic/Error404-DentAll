package dentall.service;

import dentall.domain.Driver;
import dentall.rest.dto.CreateDriverDTO;
import dentall.rest.dto.CreateDriverWithVehicleDTO;
import dentall.rest.dto.DriverWorkInfoDTO;

import java.util.List;

public interface DriverService {

    List<Driver> listAll();

    Driver createDriver(String name, String surname, String email, String phoneNumber, String vehicleRegistration, String workStartTime, String workingDays);

    DriverWorkInfoDTO getFreeDriverForDate(String date);

    Driver updateDriver(Driver driver);

    Driver updateDriver(Long id, CreateDriverDTO dto);

    Driver createDriver(CreateDriverWithVehicleDTO driver);

    void deleteDriver(Long id);
}
