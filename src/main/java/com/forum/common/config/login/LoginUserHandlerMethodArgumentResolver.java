package com.forum.common.config.login;

import com.forum.common.annotation.LoginUser;
import com.forum.common.json.JsonUser;
import com.forum.modules.user.DO.UserDO;
import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 *  获取用户信息赋值给注解
 *  @author Mr Zhang
 *  @since 2020-06-10
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(UserDO.class) && methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            String token = SecurityUtils.getSubject().getPrincipal().toString();
            UserDO userDO = JsonUser.jsonUser(token);
            return userDO;
        } catch (Exception e) {
            return new UserDO();
        }
    }
}
