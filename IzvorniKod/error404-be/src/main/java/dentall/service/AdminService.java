package dentall.service;

import dentall.domain.Admin;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AdminService {
    List<Admin> listAll();

    /**
     * Creates new admin in the system
     * @param userName username to be set for the admin
     * @param firstName First name of the admin
     * @param roleIds Set of roleIds of the roles the admin should get
     * @return Created admin object with roles and ID set
     * @throws IllegalArgumentException with message when bad arguments are given
     * @throws RequestDeniedException if admin with that username already exists
     */
    Admin createAdmin(String userName, String password, String firstName, Set<Long> roleIds);

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    Optional<Admin> findByUserName(String userName);
}
