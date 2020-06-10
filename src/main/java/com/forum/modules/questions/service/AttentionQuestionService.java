package com.forum.modules.questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.questions.DO.AttentionQuestionDO;
import com.forum.modules.questions.VO.AttentionQuestionVO;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.user.DO.UserDO;

import java.util.List;

public interface AttentionQuestionService extends IService<AttentionQuestionDO> {
    /**
     *  查询我关注的问题
     */
    List<AttentionQuestionVO> attention(UserDO userDO);

    /**
     *  存储用户关注的问题
     */
    void createAttention(QuestionVO questionVO);

    /**
     *  删除我关注的问题
     */
    void deleteAttention(QuestionVO questionVO, UserDO userDO);
}
