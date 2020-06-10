package com.forum.modules.questions.DO;

import com.baomidou.mybatisplus.annotation.TableName;
import com.forum.common.base.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *  问题表
 */
@Data
@TableName("question")
@EqualsAndHashCode(callSuper = true)
public class QuestionDO extends BaseDO {
    /**
     *  问答名称
     */
    private String questionName;
    /**
     *  浏览数量
     */
    private Integer volumeNumber;
    /**
     *  回答数量
     */
    private Integer answerNumber;
    /**
     *  关注状态值
     */
    private String attentionStatus;
    /**
     *  稍后答状态值
     */
    private String laterStatus;
    /**
     *  发表状态 0:已发表 1:未发表(存储在草稿箱)
     */
    private String publishStatus;
}
