package dentall.rest;

import dentall.domain.Admin;
import dentall.domain.AdminRole;
import dentall.service.AdminRoleService;
import dentall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRoleService roleService;

    @GetMapping("")
    public List<Admin> listAdmins(){
        return adminService.listAll();
    }

    @PostMapping("")
    public Admin createAdmin(@RequestBody CreateAdminDTO dto){
        return adminService.createAdmin(dto.getUserName(), dto.getFirstName(), dto.getRoles());
    }

    @GetMapping("/roles")
    public List<AdminRole> listRoles(){
        return roleService.listAll();
    }
}
