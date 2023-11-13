package dentall.service.impl;

import dentall.dao.UserRepository;
import dentall.domain.User;
import dentall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJpa implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAll(){
        return userRepository.findAll();
    }

}
