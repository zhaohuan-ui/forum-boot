package com.forum.common.config.begin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

/**
 *  配置文件: 启动加载
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Slf4j
@Component
public class BeginCommandLineRunner implements CommandLineRunner {

    /**
     *  初始化执行内容
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("项目目录：" + ResourceUtils.getURL("classpath:").getPath());
        log.info("**************************** 论坛启动完成 ****************************************");
    }
}
