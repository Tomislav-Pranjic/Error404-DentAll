package dentall.service.impl;

import dentall.dao.AddressRepository;
import dentall.domain.Address;
import dentall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressServiceJpa implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Override
    public List<Address> listAll() {
        return addressRepo.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepo.findById(id);
    }

    @Override
    public Address createAddress(String city, String street, Integer number) {
        Assert.hasText(city, "City must be provided.");
        Assert.hasText(street, "Street must be provided.");
        Assert.notNull(number, "Number must be provided.");
        Assert.isTrue(number > 0, "Number must be positive.");

        Assert.isTrue(addressDoesntExist(city, street, number), "Address already exists.");

        Address address = new Address(city, street, number);
        return addressRepo.save(address);
    }

    private boolean addressDoesntExist(String city, String street, Integer number) {
        return addressRepo.findAddressByCityAndStreetAndNumber(city, street, number).isEmpty();
    }
}
