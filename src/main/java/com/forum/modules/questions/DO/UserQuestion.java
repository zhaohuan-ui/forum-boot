package com.forum.modules.questions.DO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *  稍后答关系表
 */
@Data
public class UserQuestion {
    @TableId
    private Integer id;
    /**
     *  用户ID
     */
    private Integer userId;
    /**
     *  问答ID
     */
    private Integer questionId;
}
