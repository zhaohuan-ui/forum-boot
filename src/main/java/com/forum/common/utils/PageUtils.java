package com.forum.common.utils;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 *  分页查询工具类
 * @author Mr Zhang
 * @since 2020-06-16
 */
@Data
public class PageUtils {
    /**
     *  当前页
     */
    private long current;

    /**
     *  每页条数
     */
    private long size;

    /**
     * 总数
     */
    private long total;

    /**
     *  数据
     */
    private List<T> list;
}
