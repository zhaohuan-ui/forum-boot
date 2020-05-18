package com.forum.modules.login;

import com.forum.common.constan.Globals;
import com.forum.common.utils.login.LoginUtils;
import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     *  登录验证
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
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

}
