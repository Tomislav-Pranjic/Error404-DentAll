package dentall.service;

import dentall.rest.dto.CreateEmailDTO;

public interface EmailService {

    //za slanje jednostavnog maila
    String sendSimpleMail(CreateEmailDTO details);

    //za slanje maila s privitkom
    String sendMailWithAttachment(CreateEmailDTO details);
}
