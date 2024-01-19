package dentall.dao;

import dentall.domain.MedUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.Optional;

public interface MedUserRepository extends JpaRepository<MedUser, Long> {
    Optional<MedUser> findByRemoteUserId(Long remoteId);

    Optional<MedUser> findByNameAndSurnameAndDateOfBirth(@NotNull @Size(max = 32) String name, @NotNull @Size(max = 32) String surname, @NotNull java.util.Date dateOfBirth);

    Optional<MedUser> findMedUserByEmail(@NotNull @Size(max = 64) String email);

    Optional<MedUser> findMedUserByPhoneNumber(@NotNull @Size(max = 32) String phoneNumber);
}
