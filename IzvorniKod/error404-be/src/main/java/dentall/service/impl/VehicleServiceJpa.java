package dentall.service.impl;

import dentall.dao.VehicleRepository;
import dentall.domain.Vehicle;
import dentall.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceJpa implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    @Override
    public List<Vehicle> listAll() {
        return vehicleRepo.findAll();
    }

    @Override
    public Optional<Vehicle> findByRegistration(String registration) {
        return vehicleRepo.findVehicleByRegistration(registration);
    }

    @Override
    public Vehicle createVehicle(String registration, String model, String color, Integer capacity) {
        return vehicleRepo.save(new Vehicle(registration, model, color, capacity));
    }


}
