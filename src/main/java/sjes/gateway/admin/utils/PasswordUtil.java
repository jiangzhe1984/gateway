package sjes.gateway.admin.utils;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

/**
 * Created by jiangzhe on 15-11-26.
 */
public class PasswordUtil {

    public static String generatePassword(String express,String saltSource){
        MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");
        return passwordEncoder.encodePassword(express,saltSource);
    }
}
