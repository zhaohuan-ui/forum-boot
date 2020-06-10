package com.forum.modules.questions.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.forum.common.base.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  回答表
 */
@Data
@TableName("answer")
@EqualsAndHashCode(callSuper = true)
public class AnswerDO extends BaseDO{
    /**
     *  问题主键
     */
    private Integer questionId;
    /**
     * 回答/评论ID
     */
    private Integer commentId;
    /**
     * 回答内容
     */
    private String answerName;
    /**
     *  点赞数量
     */
    private Integer endorseNumber;
}
