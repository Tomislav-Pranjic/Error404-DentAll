package dentall.service.impl;

import dentall.dao.AdminRoleRepository;
import dentall.domain.AdminRole;
import dentall.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AdminRoleServiceJpa implements AdminRoleService {

    @Autowired
    private AdminRoleRepository roleRepo;

    @Override
    public List<AdminRole> listAll() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<AdminRole> findById(Long id) {
        Assert.notNull(id, "ID must be given.");
        return roleRepo.findById(id);
    }

    @Override
    public int countByRoleId(Long roleId) {
        return roleRepo.countByRoleId(roleId);
    }
}
