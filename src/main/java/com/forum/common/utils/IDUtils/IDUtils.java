package com.forum.common.utils.IDUtils;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.util.UUID;

/**
 *  主键ID工具类
 */
public class IDUtils {

    /**
     *  生成10位ID
     * @return
     */
    public static Integer get10ID(){
        long longId = IdWorker.getId();
        String str = String.valueOf(longId);
        str = str.substring(0,3) + str.substring(8,9)+str.substring(13);
        return Integer.valueOf(str);
    }

    /**
     *  19位Long类型ID
     */
    public static Long getLongID(){
        return IdWorker.getId();
    }

    /**
     *  生成Integer类型纯数字id(位数不固定)
     * @return
     */
    /*public static Integer getId(){
        Integer orderId = UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }*/
}