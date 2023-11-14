package dentall.rest;

import dentall.domain.MedUser;
import dentall.service.MedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MedUserController {

    @Autowired
    private MedUserService medUserService;

    @GetMapping("")
    public List<MedUser> listUsers(){
        return medUserService.listAll();
    }

}
