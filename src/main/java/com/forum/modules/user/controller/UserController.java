package com.forum.modules.user.controller;

import com.forum.common.constan.Globals;
import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.common.utils.login.LoginUtils;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  登录验证
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult<Object> login(@RequestBody UserDO userRequest, HttpSession session) {
        try {
            UserDO userDO = userService.getUser(userRequest.getUsername());
            // 将用户信息保存到session中
            LoginUtils.setUserDO(userDO);
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
    @ResponseBody
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
    @ResponseBody
    public HttpResult<Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Globals.USER_SESSION); // 注销该操作用户
        return HttpResultUtil.success("退出成功!", null);
    }

    /**
     *  退出登录功能
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult<Object> insert() {
        UserDO userDO = new UserDO();
        userDO.setId(500);
        userDO.setNickName("你丫闭嘴");
        userDO.setUsername("test");
        userService.save(userDO);
        return HttpResultUtil.success("保存成功!");
    }

    /**
     *  登录验证
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult<Object> updateUser(@RequestBody UserDO user) {
        userService.updateUser(user);
        return HttpResultUtil.success("更新成功!",null);
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public HttpResult<Object> getUser(String username){
        UserDO user = userService.getUser(username);
        user.setPassword(UUID.randomUUID().toString());
        return HttpResultUtil.success("查询成功!",user);
    }
}
