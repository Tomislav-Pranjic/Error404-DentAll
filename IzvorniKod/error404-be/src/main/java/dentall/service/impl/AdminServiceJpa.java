package dentall.service.impl;

import dentall.dao.AdminRepository;
import dentall.domain.Admin;
import dentall.domain.AdminRole;
import dentall.service.AdminService;
import dentall.service.RequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdminServiceJpa implements AdminService {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private AdminRoleServiceJpa roleService;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private static final String USERNAME_FORMAT = "[a-z]+[0-9]*";

    @Override
    public List<Admin> listAll() {
        return adminRepo.findAll();
    }

    @Override
    public Admin createAdmin(String userName, String password, String firstName, Set<Long> roleIds) {
        Assert.hasText(password, "Password must be given");
        Assert.isTrue(password.length() >= 8, "Password must be at least 8 characters long");

        Assert.hasText(userName, "Username must be given");
        Assert.isTrue(userName.matches(USERNAME_FORMAT),
                "Username must be all lowercase and start with a letter, not '"
                        + userName + "'"
        );
        Assert.isTrue((4 <= userName.length()) && (20 >= userName.length()),
                "Username must contain at least 4 and at most 20 characters.");

        if(adminRepo.countByUserName(userName) > 0){
            throw new RequestDeniedException(
                    "Admin with user name '" + userName + "' already exists!"
            );
        }

        Assert.notNull(roleIds, "Admin roles must be defined.");
        Assert.isTrue(!roleIds.isEmpty(), "Admin must have at least one role.");

        Admin admin = new Admin(userName, encoder.encode(password), firstName);

        roleIds.stream().forEach(roleId -> {
//            Assert.isTrue(roleService.countByRoleId(roleId) >= 1, "Role with id '" + roleId + "' does not exist.");
            Optional<AdminRole> roleById = roleService.findById(roleId);

            Assert.isTrue(roleById.isPresent(), "Role with id '" + roleId + "' does not exist.");

            roleById.ifPresent(admin::addRole);
        });
//        Assert.isTrue(false, "Roles: " + admin.getRoles());

        return adminRepo.save(admin);
    }

    @Override
    public Optional<Admin> findByUserName(String userName) {
        return adminRepo.findByUserName(userName);
    }
}
