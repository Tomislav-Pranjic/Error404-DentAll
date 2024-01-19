package dentall.service.impl;

import dentall.dao.VehicleRepository;
import dentall.domain.Vehicle;
import dentall.service.VehicleService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        Vehicle v = vehicleRepo.findVehicleByRegistration(vehicle.getRegistration()).orElseThrow(() -> new ItemNotFoundException("Vehicle with registration: '" + vehicle.getRegistration() + "' not found"));
        return vehicleRepo.saveAndFlush(vehicle);
    }


}
