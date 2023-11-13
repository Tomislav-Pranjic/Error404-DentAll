package dentall.service.impl;

import dentall.dao.AccommodationRepository;
import dentall.domain.Accommodation;
import dentall.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationServiceJpa implements AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepo;

    @Override
    public List<Accommodation> listAll(){
        return accommodationRepo.findAll();
    }
}
