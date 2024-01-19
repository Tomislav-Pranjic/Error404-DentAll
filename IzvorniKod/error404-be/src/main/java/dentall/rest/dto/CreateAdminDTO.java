package dentall.rest.dto;

import java.util.Set;

public class CreateAdminDTO {
    private String userName;

    private String password;
    private Set<Long> roleIds;

    public String getUserName() {
        return userName;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public String getPassword() {
        return password;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
