package dentall.dao;

import dentall.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    int countByUserName(String userName);

    Optional<Admin> findByUserName(String userName);
}
