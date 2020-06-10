package com.forum.common.base.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.forum.common.json.JsonUser;
import com.forum.modules.user.DO.UserDO;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  自动填充处理类
 *  @author  : Mr Zhang
 *  @Date : 20-05-11 下午1:15
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        UserDO userDO = JsonUser.jsonUser(SecurityUtils.getSubject().getPrincipal().toString());
        this.setFieldValByName("createBy",userDO.getId(),metaObject);
        this.setFieldValByName("flag",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        /*UserDO userDO = JsonUser.jsonUser(SecurityUtils.getSubject().getPrincipal().toString());
        this.setFieldValByName("createBy",userDO.getId(),metaObject);*/
    }
}
