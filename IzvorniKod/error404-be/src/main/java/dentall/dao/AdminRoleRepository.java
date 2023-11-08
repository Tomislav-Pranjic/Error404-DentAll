package dentall.dao;

import dentall.domain.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {
    int countByRoleName(String roleName);
    int countByRoleId(Long roleId);
}
