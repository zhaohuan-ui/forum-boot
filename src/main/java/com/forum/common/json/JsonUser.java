package com.forum.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forum.modules.user.DO.UserDO;

/**
 *  Bean对象/JSON 转换工具类
 */
public class JsonUser {

    public static String jsonStr(UserDO userDO){
        return JSONObject.toJSONString(userDO);
    }

    public static UserDO jsonUser(String token){
        UserDO userDO = JSON.parseObject(token,UserDO.class);
        return userDO;
    }
}
