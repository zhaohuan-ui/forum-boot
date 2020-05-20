package com.forum.modules.user.controller;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 *  个人主页信息
 * @author zhaohuan
 * @since 2020-05-03
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *  新建用户信息
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult<Object> insert(UserDO userDO) {
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
