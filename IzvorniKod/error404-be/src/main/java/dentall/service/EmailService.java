package dentall.service;

import dentall.rest.dto.CreateEmailForDriverDTO;
import dentall.rest.dto.CreateEmailForMedUserDTO;

public interface EmailService {

    String sendEmailToMedUser(CreateEmailForMedUserDTO details);
    String sendEmailToDriver(CreateEmailForDriverDTO details);

}
