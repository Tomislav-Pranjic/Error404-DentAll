package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity(name = "ADMIN_ULOGA")
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uloga_id")
    private Long roleId;

    @Column(unique = true, name = "uloga_ime")
    @Size(max = 25)
    @NotNull
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminRole adminRole = (AdminRole) o;
        return Objects.equals(roleId, adminRole.roleId) && Objects.equals(roleName, adminRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return "AdminRole{" +
                "id=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
