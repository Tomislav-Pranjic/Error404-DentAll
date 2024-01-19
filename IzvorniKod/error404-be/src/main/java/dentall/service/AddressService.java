package dentall.service;

import dentall.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> listAll();

    Optional<Address> findById(Long id);

    Address createAddress(String city, String street, Integer number);
}
