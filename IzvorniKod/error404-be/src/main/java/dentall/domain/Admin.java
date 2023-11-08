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

    private String Name;

    @ManyToMany
    private Set<AdminRole> roles;

    public Admin(String userName, String name) {
        this.userName = userName;
        Name = name;

        roles = new HashSet<>();
    }

    public Admin() {
        this.userName = null;
        this.Name = null;
        this.roles = new HashSet<>();
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return Name;
    }

    public Set<AdminRole> getRoles() {
        return roles;
    }

    public void addRole(AdminRole role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + adminId +
                ", userName='" + userName + '\'' +
                ", Name='" + Name + '\'' +
                ", roles={" + roles.toString() + "}" +
                '}';
    }
}
