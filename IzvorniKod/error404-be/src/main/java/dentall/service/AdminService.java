package dentall.service;

import dentall.domain.Admin;

import java.util.List;
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
    Admin createAdmin(String userName, String firstName, Set<Long> roleIds);
}