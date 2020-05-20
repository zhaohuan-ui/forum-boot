package com.forum.common.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.forum.common.utils.redis.RedisUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *  自动填充处理类
 *  @author  : zhangchunfeng
 *  @Date : 20-05-11 下午1:15
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        //this.setFieldValByName("createBy",1,metaObject);
        this.setFieldValByName("flag",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        //this.setFieldValByName("createBy",1,metaObject);
    }
}
