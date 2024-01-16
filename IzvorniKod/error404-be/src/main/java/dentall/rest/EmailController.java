package dentall.rest;

import dentall.rest.dto.CreateEmailDTO;
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

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody CreateEmailDTO details) {
        String status = emailService.sendSimpleMail(details);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(
            @RequestBody CreateEmailDTO details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}
