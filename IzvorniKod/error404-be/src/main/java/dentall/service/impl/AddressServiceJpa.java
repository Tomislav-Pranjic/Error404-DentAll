package dentall.service.impl;

import dentall.dao.AddressRepository;
import dentall.domain.Address;
import dentall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceJpa implements AddressService {

    @Autowired
    private AddressRepository addressRepo;

    @Override
    public List<Address> listAll() {
        return addressRepo.findAll();
    }
}
