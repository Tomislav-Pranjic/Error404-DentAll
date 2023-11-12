package dentall.service.impl;

import dentall.dao.AccomodationRepository;
import dentall.domain.Accomodation;
import dentall.service.AccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccomodationServiceJpa implements AccomodationService {

    @Autowired
    private AccomodationRepository accomodationRepo;

    @Override
    public List<Accomodation> listAll(){
        return accomodationRepo.findAll();
    }
}
