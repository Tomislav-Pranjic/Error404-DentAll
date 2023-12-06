package dentall.rest;

import dentall.domain.Admin;
import dentall.domain.AdminRole;
import dentall.rest.dto.CreateAdminDTO;
import dentall.service.AdminRoleService;
import dentall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@PreAuthorize("hasRole('ROLE_SMJESTAJNI')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService roleService;

    private PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @GetMapping("")
    public List<Admin> listAdmins(){
        return adminService.listAll();
    }

    @PostMapping("")
    public Admin createAdmin(@RequestBody CreateAdminDTO dto){
        return adminService.createAdmin(
                        dto.getUserName(),
                        dto.getPassword(),
                        dto.getFirstName(),
                        dto.getRoleIds()
                );
    }

    @GetMapping("/roles")
    public List<AdminRole> listRoles(){
        return roleService.listAll();
    }
}
