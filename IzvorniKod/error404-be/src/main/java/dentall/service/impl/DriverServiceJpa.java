package dentall.service.impl;

import dentall.dao.DriverRepository;
import dentall.domain.Driver;
import dentall.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceJpa implements DriverService {

    @Autowired
    private DriverRepository transportRepository;

    @Override
    public List<Driver> listAll(){
        return transportRepository.findAll();
    }

}
