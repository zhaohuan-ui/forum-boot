package com.forum.modules.questions.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.forum.common.base.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  关注问题表
 */
@Data
@TableName("attention_question")
@EqualsAndHashCode(callSuper = true)
public class AttentionQuestionDO extends BaseDO {
    /**
     *  问题ID
     */
    public Integer questionId;
    /**
     *  问答名称
     */
    private String questionName;
}
