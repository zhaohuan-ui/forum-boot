package com.forum.modules.log.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.forum.common.base.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志表
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class LogDO extends BaseDO {
    /**
     * 操作用户
     */
    private Integer userId;

    /**
     * 操作内容
     */
    private String message;

    /**
     * 操作类型
     */
    private Integer type;

    /**
     * 备用字段一
     */
    private String spare1;

    /**
     * 备用字段二
     */
    private String spare2;

    /**
     * 备用字段三
     */
    private Integer spare3;


}
