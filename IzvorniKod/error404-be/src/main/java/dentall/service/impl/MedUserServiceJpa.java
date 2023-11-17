package dentall.service.impl;

import dentall.dao.MedUserRepository;
import dentall.domain.MedUser;
import dentall.service.MedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedUserServiceJpa implements MedUserService {

    @Autowired
    private MedUserRepository medUserRepository;

    @Override
    public List<MedUser> listAll(){
        return medUserRepository.findAll();
    }

}
