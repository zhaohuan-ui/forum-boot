package com.forum.common.utils.login;

import com.forum.common.constan.Globals;
import com.forum.modules.user.DO.UserDO;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 *  用户登录后信息保存
 */
//@Component
public class LoginUtils {

    /**
     *  创建存储session
     * @return
     */
    private static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     *  获取用户信息
     * @return
     */
    public static UserDO getUserDO(){
        HttpSession session = getRequest().getSession();
        return (UserDO)session.getAttribute(Globals.USER_SESSION);
    }

    /**
     *  存储用户信息
     */
    public static void setUserDO(UserDO userDO){
        HttpSession session = getRequest().getSession();
        if(userDO != null){
            session.setAttribute(Globals.USER_SESSION,userDO);
            session.setMaxInactiveInterval(60 * 60 * 6); // 6小时无活动,session失效
        }
    }
}
