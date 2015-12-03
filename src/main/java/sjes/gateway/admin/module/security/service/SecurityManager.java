package sjes.gateway.admin.module.security.service;

import sjes.gateway.admin.module.security.UserContext;

/**
 * Created by jiangzhe on 15-11-17.
 */
public interface SecurityManager {

    UserContext getUserContext(String paramString);


}
