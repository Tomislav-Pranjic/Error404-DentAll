package dentall.dao;

import dentall.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findDriverByDriverId(Long driverId);

    Optional<Driver> findDriverByNameAndSurnameAndPhoneNumber(String name, String surname, String phoneNumber);

    Optional<Driver> findDriverByNameAndSurnameAndEmail(String name, String surname, String email);

    Optional<Driver> findDriverByPhoneNumber(String phoneNumber);

    Optional<Driver> findDriverByEmail(String email);
}
