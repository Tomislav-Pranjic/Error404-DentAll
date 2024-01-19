package dentall.service;

import dentall.domain.AccommodationType;
import dentall.domain.Address;
import dentall.domain.MedUser;
import dentall.rest.dto.CreateMedUserDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MedUserService {

    List<MedUser> listAll();

    MedUser createMedUser(String firstName, String lastName, String email, String phoneNumber, Long accTypePreferenceId, String dateOfBirth);

    Optional<MedUser> getMedUserByRemoteId(Long remoteId);

    MedUser createMedUser(MedUser medUser);

    MedUser updateMedUser(Long id, CreateMedUserDTO dto);

    void deleteMedUser(Long id);
}
