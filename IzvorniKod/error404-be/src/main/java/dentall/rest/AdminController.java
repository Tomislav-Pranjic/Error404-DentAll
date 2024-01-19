package dentall.rest;

import dentall.domain.Admin;
import dentall.domain.AdminRole;
import dentall.rest.dto.CreateAdminDTO;
import dentall.service.AdminRoleService;
import dentall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable("id") Long id){
        return adminService.findById(id).orElse(null);
    }
    
    @PostMapping("")
    public Admin createAdmin(@RequestBody CreateAdminDTO dto){
        return adminService.createAdmin(
                        dto.getUserName(),
                        dto.getPassword(),
                        dto.getRoleIds()
                );
    }

    @PatchMapping("/{id}")
    public Admin updateAdmin(@PathVariable("id") Long id, @RequestBody CreateAdminDTO dto) {
        return adminService.updateAdmin(
                id,
                dto.getUserName(),
                dto.getPassword(),
                dto.getRoleIds()
        );
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdmin(@PathVariable("id") Long id){
        adminService.deleteAdmin(id);
    }

    @GetMapping("/roles")
    public List<AdminRole> listRoles(){
        return roleService.listAll();
    }
}
