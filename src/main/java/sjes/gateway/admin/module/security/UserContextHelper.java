package sjes.gateway.admin.module.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import sjes.gateway.admin.module.base.domain.AclUser;

/**
 * 获取登陆后用户信息的工具类
 */
public class UserContextHelper {

	public static UserContext getUserContext()  {
	    return ((getAuthentication() != null) && (getAuthentication().getPrincipal() instanceof UserContext)) ? (UserContext)getAuthentication().getPrincipal() : null;
	  }

	  public static SecurityContext getContext()
	  {
	    return SecurityContextHolder.getContext();
	  }

	  public static Authentication getAuthentication() {
	    return (getContext() == null) ? null : getContext().getAuthentication();
	  }

	  public static AclUser getUser()
	  {
	    return (getUserContext() == null) ? null : getUserContext().getUser();
	  }


}
