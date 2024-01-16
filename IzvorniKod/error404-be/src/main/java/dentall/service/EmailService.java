package dentall.service;

import dentall.rest.dto.CreateEmailDTO;

public interface EmailService {

    //za slanje jednostavnog maila
    String sendSimpleEmail(CreateEmailDTO details);

    //za slanje maila s privitkom
    String sendEmailWithAttachment(CreateEmailDTO details);
}
