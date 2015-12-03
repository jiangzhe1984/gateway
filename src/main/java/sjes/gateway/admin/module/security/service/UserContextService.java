package sjes.gateway.admin.module.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sjes.gateway.admin.module.security.UserContext;

/**
 * Created by jiangzhe on 15-11-17.
 */
public class UserContextService implements UserDetailsService {

    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserContext userContext = this.securityManager.getUserContext(username);

        if (userContext == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return userContext;
    }
}
