package com.forum.common.config.shiro;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  配置文件: Shiro权限
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Configuration
public class ShiroConfig {

    /**
     * 不需要在此处配置权限页面,因为上面的ShiroFilterFactoryBean已经配置过,
     * 但是此处必须存在,因为shiro-spring-boot-web-starter或查找此Bean,没有则会报错
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        return new DefaultShiroFilterChainDefinition();
    }

    // 过滤链
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 添加自己的过滤器并且取名
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new FormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new HashMap<>(16);
        List<Map<String, String>> perms = getShiroFilterProperties().getPerms();
        perms.forEach(perm -> filterChainDefinitionMap.put(perm.get("key"),perm.get("value")));
        // filterChainDefinitionMap.put("/login/user", "anon");
        // 过滤链定义，从上向下顺序执行，一般将 /** 放在最下边
        // filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    // 创建realm对象 自定义的 UserRealm类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // shiro的生命周期
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    // 获取动态过滤链
    @Bean
    public ShiroFilterProperties getShiroFilterProperties() {
        return new ShiroFilterProperties();
    }
}
