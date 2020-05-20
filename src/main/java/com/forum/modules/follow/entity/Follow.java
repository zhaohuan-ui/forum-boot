package com.forum.modules.follow.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 关注表
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户
     */
    private Integer userId;

    /**
     * 关注用户
     */
    private Integer followUser;

    /**
     * 被用户关注
     */
    private Integer byFollowUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最新更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 逻辑删除
     */
    private Integer flags;

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
