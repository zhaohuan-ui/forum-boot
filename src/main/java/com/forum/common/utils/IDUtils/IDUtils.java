package com.forum.common.utils.IDUtils;

import java.util.UUID;

public class IDUtils {

    /**
     *  生成Integer类型纯数字id
     * @return
     */
    public static Integer getId(){
        Integer orderId = UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }
}
