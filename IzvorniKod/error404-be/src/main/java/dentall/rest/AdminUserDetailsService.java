package dentall.rest;

import dentall.domain.Admin;
import dentall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class AdminUserDetailsService implements UserDetailsService {
    @Value("${dentall.admin.password}")
    private String adminPasswordHash;

    @Autowired
    private AdminService adminService;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("owner")){
            return new User(
                    username,
                    adminPasswordHash,
                    commaSeparatedStringToAuthorityList("ROLE_SMJESTAJNI, ROLE_KORISNICKI, ROLE_PRIJEVOZNI")
            );
        }

        Admin admin = adminService.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username: '" + username + "' not found")
        );

        return new User(
                username,
                admin.getPasswordHash().substring(admin.getPasswordHash().indexOf("}") + 1),
                commaSeparatedStringToAuthorityList(admin.getRolesString())
        );

    }
}
