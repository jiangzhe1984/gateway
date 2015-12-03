package sjes.gateway.admin.module.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import sjes.gateway.admin.module.base.domain.AclUser;
import sjes.gateway.admin.module.base.domain.Authority;
import sjes.gateway.admin.module.base.domain.UserAuthority;
import sjes.gateway.admin.module.security.UserContext;
import sjes.gateway.admin.module.base.repository.AuthorityRepository;
import sjes.gateway.admin.module.base.repository.RoleUserRepository;
import sjes.gateway.admin.module.base.repository.UserAuthorityRepository;
import sjes.gateway.admin.module.base.service.AclUserService;
import sjes.gateway.admin.module.security.service.SecurityManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangzhe on 15-11-17.
 */
public class SecurityManagerImpl implements SecurityManager {

    @Autowired
    private AclUserService aclUserService;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;


    private AclUser getUser(String username){
        return aclUserService.findByName(username);
    }

    @Override
    public UserContext getUserContext(String paramString) {
        AclUser user = getUser(paramString);

        if (user == null) {
            return null;
        }
        UserContext userContext = new UserContext(user);
        userContext.setUserAuthorities(getUserAuthorities(user));
        userContext.setUserRoles(roleUserRepository.findByUser(user));
        return userContext;

    }

    public List<UserAuthority> getUserAuthorities(AclUser user)throws DataAccessException {

        List<UserAuthority> userAuthorities = null;
        if ((user.getUserMgrType() == null) || (user.getUserMgrType().intValue() != 1400)) {
            userAuthorities = userAuthorityRepository.findListByUser(user);
        } else {
            userAuthorities = new ArrayList();
            List<Authority> authorities = authorityRepository.findAll();
            for (Authority authority : authorities) {
                UserAuthority userAuthority = new UserAuthority();
                userAuthority.setAuthority(authority);
                userAuthority.setSecurityuser(user);
                userAuthorities.add(userAuthority);
            }
        }

        if (userAuthorities == null) {
            throw new DataIntegrityViolationException("user:" + user.getFullName() + " havn't userAuthorities");
        }


        return userAuthorities;
    }
}
