package com.forum.common.base.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  实体类继承对象
 * @author  : zhangchunfeng
 * @Date : 20-05-11 下午1:15
 */

@Data
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建/更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer createBy;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer flag;
}
