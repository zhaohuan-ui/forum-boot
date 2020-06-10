package com.forum.common.config.shiro;

import com.forum.common.json.JsonUser;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  获取 授权/认证 信息
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    // 执行授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpInfo = new SimpleAuthorizationInfo();
        simpInfo.addStringPermission("jwt"); // 获取权限
        return simpInfo;
    }

    // 执行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        // 获取登录的信息
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        // 从数据库获取登录者的信息
        UserDO userDO = userService.getUser(userToken.getUsername());
        // 没有这个用户
        if (userDO == null) {
            return null;
        }
        // 获取用户的session
        Session session = subject.getSession();
        session.setAttribute("loginUser", userDO);
        // 判断登录的用户名密码 匹配数据库是否正确
        if (!userToken.getUsername().equals(userDO.getUsername())) {
            return null;// 抛出异常
        }
        // 密码认证 shiro做
        return new SimpleAuthenticationInfo(JsonUser.jsonStr(userDO), userDO.getPassword(), getName());
    }
}
