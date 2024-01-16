package dentall.dao;

import dentall.domain.MedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedUserRepository extends JpaRepository<MedUser, Long> {
    Optional<MedUser> findByRemoteUserId(Long remoteId);
}
