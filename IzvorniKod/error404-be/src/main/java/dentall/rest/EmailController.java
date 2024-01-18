package dentall.rest;

import dentall.domain.UserTreatmentInfo;
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

    @PostMapping("/sendEmails")
    public String sendEmails(@RequestBody UserTreatmentInfo details) {
        String s1 = emailService.sendEmailToMedUser(details);
        String s2 = emailService.sendEmailToArrivalDriver(details);
        String s3 = emailService.sendEmailToDepartureDriver(details);
        return s1 + "\n" + s2 + "\n" + s3;
    }


}
