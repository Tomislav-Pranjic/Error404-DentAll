package dentall.rest;

import dentall.rest.dto.CreateEmailForDriverDTO;
import dentall.rest.dto.CreateEmailForMedUserDTO;
import dentall.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@PreAuthorize("hasRole('ROLE_SMJESTAJNI') or hasRole('ROLE_KORISNICKI') or hasRole('ROLE_PRIJEVOZNI')")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendEmailToMedUser")
    public String sendEmailToMedUser(@RequestBody CreateEmailForMedUserDTO details) {
        return emailService.sendEmailToMedUser(details);
    }

    @PostMapping("/sendEmailToDriver")
    public String sendEmailToDriver(@RequestBody CreateEmailForDriverDTO details) {
        return emailService.sendEmailToDriver(details);
    }

}
