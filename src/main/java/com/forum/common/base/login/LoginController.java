package com.forum.common.base.login;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *  控制器: 登录/登出
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     *  登录验证
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public HttpResult<Object> login(@RequestBody UserDO userDO) {
        try {
            //获取当前的用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userDO.getUsername(), userDO.getPassword());
            subject.login(usernamePasswordToken);
            log.info("[ "+userDO.getUsername()+" ]用户登陆成功");
            return HttpResultUtil.success("登录成功!",userDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HttpResultUtil.error("登录失败!");
    }

    /**
     *  获取token
     * @param token
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public HttpResult<Object> info(String token) {
        Map<String, String> map = new HashMap<>();
        map.put("name", token);
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return HttpResultUtil.success("验证成功!",map);
    }

    /**
     *  退出登录功能
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public HttpResult<Object> logout(String token) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.info("[ "+token+" ]用户退出登录");
        return HttpResultUtil.success("退出成功!", null);
    }

}
