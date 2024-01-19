package dentall.dao;

import dentall.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Optional<Vehicle> findVehicleByRegistration(String vehicleRegistration);
}
