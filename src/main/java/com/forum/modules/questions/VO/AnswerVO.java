package com.forum.modules.questions.VO;

import com.forum.modules.questions.DO.AnswerDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerVO extends AnswerDO {
    /**
     *  返回评论者昵称
     */
    private String nickName;
    /**
     *  返回评论者头像路径
     */
    private String imgUrl;
    /**
     *  关注者
     */
    private Integer attentionNumber;
    /**
     *  浏览数量
     */
    private Integer volumeNumber;
    /**
     *  返回回答用户的评论信息集合
     */
    private List<AnswerVO> comments;
}
