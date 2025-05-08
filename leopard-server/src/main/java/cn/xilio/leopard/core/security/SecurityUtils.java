package cn.xilio.leopard.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

public class SecurityUtils {
    /**
     * 获取当前登录用户ID
     *
     * @return 用户唯一标识
     */
    public static String getLoginIdAsString() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!ObjectUtils.isEmpty(authentication) && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return userDetails.getUsername();

            }
        }
        return null;
    }

    public static TokenInfo login(Object id) {

        return null;
    }

    /**
     * 登陆
     * @param id 唯一标识 登陆ID
     * @param timeout 此次登录 token 的有效期, 单位:秒
     * @return 登陆令牌
     */
    public static TokenInfo login(Object id, long timeout) {

        return null;
    }

    public static void logout() {


    }
    public static void logout(Object id) {


    }

    public static boolean isLogin() {

        return false;
    }


}
