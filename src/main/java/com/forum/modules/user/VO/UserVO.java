package com.forum.modules.user.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    /**
     * 自增唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * MD5密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所在行业
     */
    private String industry;

    /**
     * 职业经历
     */
    private String career;

    /**
     * 个人简介
     */
    private String education;

    /**
     * 教育经历
     */
    private String introduction;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 星标量
     */
    private Integer starScalar;

    /**
     * 关联三方表—关注表user_id
     */
    private Integer followId;

    /**
     * 角色
     */
    private Integer roleId;

    /**
     * 关联三方表—文章表
     */
    private Integer articleId;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 逻辑删除
     */
    private Integer flags;

    /**
     *  备注
     */
    private String remark;
}
