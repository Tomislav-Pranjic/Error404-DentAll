package dentall.dao;

import dentall.domain.MedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedUserRepository extends JpaRepository<MedUser, Long> {
    
}
