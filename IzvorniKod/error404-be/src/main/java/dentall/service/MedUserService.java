package dentall.service;

import dentall.domain.AccommodationType;
import dentall.domain.Address;
import dentall.domain.MedUser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MedUserService {

    List<MedUser> listAll();

    MedUser createMedUser(String firstName, String lastName, String email, String phoneNumber, String arrivalDate, Long arrivalAddressId, String departureDate, Long departureAddressId, Long accTypePreferenceId);

}
