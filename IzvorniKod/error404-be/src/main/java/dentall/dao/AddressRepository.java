package dentall.dao;

import dentall.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findAddressByAddressId(Long addressId);

    Optional<Address> findAddressByCityAndStreetAndNumber(String city, String street, Integer number);
}
