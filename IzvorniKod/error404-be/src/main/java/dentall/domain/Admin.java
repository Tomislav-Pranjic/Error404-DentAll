package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY znači ga automatski generira baza podataka
    private Long adminId;

    @Column(unique = true)
    @NotNull
    @Size(min=4, max=20)
    private String userName;

    @NotNull
    @Size(min=8)
    private String passwordHash;

    private String firstName;

    @ManyToMany
    private Set<AdminRole> roles;

    public Admin(String userName, String passwordHash, String firstName) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.firstName = firstName;

        roles = new HashSet<>();
    }

    public Admin() {
        this.userName = null;
        this.passwordHash = null;
        this.firstName = null;
        this.roles = new HashSet<>();
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<AdminRole> getRoles() {
        return roles;
    }


    public String getRolesString() {
        StringBuilder rolesString = new StringBuilder();
        for (AdminRole role : roles) {
            rolesString.append("ROLE_").append(role.getRoleName()).append(",");
        }
        return rolesString.toString();
    }

    public void addRole(AdminRole role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + adminId +
                ", userName='" + userName + '\'' +
                ", Name='" + firstName + '\'' +
                ", roles={" + roles.toString() + "}" +
                '}';
    }
}
