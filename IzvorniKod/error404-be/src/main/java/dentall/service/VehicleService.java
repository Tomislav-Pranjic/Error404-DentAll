package dentall.service;

import dentall.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> listAll();

    Optional<Vehicle> findByRegistration(String registration);

    Vehicle createVehicle(String registration, String model, String color, Integer capacity);
}
