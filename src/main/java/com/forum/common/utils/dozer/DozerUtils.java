package com.forum.common.utils.dozer;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 *  对象转换工具类
 * @author  : zhangchunfeng
 * @Date : 20-05-11 下午1:15
 */
public class DozerUtils {

    static Mapper mapper = new DozerBeanMapper();

    /**
     *  转换单个VO对象,生成实例化对象
     * @param source
     * @param destType
     * @param <U>
     * @return
     */
    public static <U> U map(final Object source,final Class<U> destType) {
        return mapper.map(source, destType);
    }
}
