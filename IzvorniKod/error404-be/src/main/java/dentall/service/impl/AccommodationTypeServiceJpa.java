package dentall.service.impl;

import dentall.dao.AccommodationTypeRepository;
import dentall.domain.AccommodationType;
import dentall.service.AccommodationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationTypeServiceJpa implements AccommodationTypeService {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepo;

    @Override
    public List<AccommodationType> listAll() {
        return accommodationTypeRepo.findAll();
    }
}
