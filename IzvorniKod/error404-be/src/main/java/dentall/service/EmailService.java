package dentall.service;

import dentall.domain.UserTreatmentInfo;

public interface EmailService {

    String sendEmailToMedUser(UserTreatmentInfo details);
    String sendEmailToArrivalDriver(UserTreatmentInfo details);
    String sendEmailToDepartureDriver(UserTreatmentInfo details);

}
