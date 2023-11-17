package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Admin {
    @Id
    @Column(unique = true)
    @NotNull
    @Size(min=4, max=20)
    private String userName;

    @NotNull
    @Size(min=8)
    private String passwordHash;

    @ManyToMany
    private Set<AdminRole> roles;

    public Admin(String userName, String passwordHash) {
        this.userName = userName;
        this.passwordHash = passwordHash;

        roles = new HashSet<>();
    }

    public Admin() {
        this.userName = null;
        this.passwordHash = null;
        this.roles = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public String passwordHashForAuth() {
        return passwordHash;
    }

    public Set<AdminRole> getRoles() {
        return roles;
    }


    public String rolesStringForAuth() {
        StringBuilder rolesString = new StringBuilder();
        for (AdminRole role : roles) {
            rolesString.append("ROLE_").append(role.getRoleName()).append(",");
        }
        return rolesString.deleteCharAt(rolesString.lastIndexOf(",")).toString();
    }

    public void addRole(AdminRole role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                ", userName='" + userName + '\'' +
                ", roles={" + roles.toString() + "}" +
                '}';
    }
}
