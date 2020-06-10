package com.forum.common.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *  配置文件: 动态获取过滤链
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Data
@Component
@ConfigurationProperties(prefix = "permission-config")
public class ShiroFilterProperties {
    List<Map<String, String>> perms;
}

