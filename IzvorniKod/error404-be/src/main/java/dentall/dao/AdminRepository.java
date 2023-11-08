package dentall.dao;

import dentall.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    int countByUserName(String userName);
}
