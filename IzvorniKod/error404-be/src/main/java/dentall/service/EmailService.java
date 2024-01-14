package dentall.service;

import dentall.rest.dto.EmailDetails;

public interface EmailService {

    //za slanje jednostavnog maila
    String sendSimpleMail(EmailDetails details);

    //za slanje maila s privitkom
    String sendMailWithAttachment(EmailDetails details);
}
