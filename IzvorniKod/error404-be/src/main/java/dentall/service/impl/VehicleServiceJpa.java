package dentall.service.impl;

import dentall.dao.VehicleRepository;
import dentall.domain.Vehicle;
import dentall.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceJpa implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepo;

    private final String REGISTRATION_FORMAT1 = "[A-Z]{2}[0-9]{4}[A-Z]{2}";
    private final String REGISTRATION_FORMAT2 = "[A-Z]{2}[0-9]{3}[A-Z]{2}";

    @Override
    public List<Vehicle> listAll() {
        return vehicleRepo.findAll();
    }
}
