package dentall.service;

import dentall.domain.AdminRole;

import java.util.List;
import java.util.Optional;

public interface AdminRoleService {
    List<AdminRole> listAll();

    Optional<AdminRole> findById(Long id);

    int countByRoleId(Long roleId);
}
