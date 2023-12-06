package dentall.rest.security;

import dentall.domain.Admin;
import dentall.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;

@Service
public class AdminUserDetailsService implements UserDetailsService {
    @Value("${dentall.admin.password}")
    private String adminPasswordHash;

    @Autowired
    private AdminService adminService;

    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private Logger logger = LoggerFactory.getLogger(AdminUserDetailsService.class);


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("owner")){
            logger.debug("Owner logged in");
            return new User(
                    username,
                    adminPasswordHash,
                    commaSeparatedStringToAuthorityList("ROLE_SMJESTAJNI, ROLE_KORISNICKI, ROLE_PRIJEVOZNI")
            );
        }

        logger.debug("Admin logged in");

        Admin admin = adminService.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username: '" + username + "' not found")
        );

        return new User(
                username,
                admin.passwordHashForAuth().substring(admin.passwordHashForAuth().indexOf("}") + 1),
                commaSeparatedStringToAuthorityList(admin.rolesStringForAuth())
        );

    }
}
