package dentall.rest;

import dentall.rest.dto.CreateEmailDTO;
import dentall.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody CreateEmailDTO details) {
        return emailService.sendSimpleEmail(details);
    }

    @PostMapping("/sendEmailWithAttachment")
    public String sendEmailWithAttachment(
            @RequestBody CreateEmailDTO details) {
        return emailService.sendEmailWithAttachment(details);
    }
}
