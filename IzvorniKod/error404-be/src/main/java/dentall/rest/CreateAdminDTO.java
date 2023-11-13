package dentall.rest;

import java.util.Set;

public class CreateAdminDTO {
    private String userName;

    private String password;
    private String firstName;
    private Set<Long> roles;

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }
}
