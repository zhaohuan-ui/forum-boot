package com.forum.modules.questions.service;

import com.forum.modules.questions.VO.AttentionQuestionVO;
import com.forum.modules.questions.VO.QuestionVO;

import java.util.List;

public interface AttentionQuestionService {
    /**
     *  查询我关注的问题
     */
    List<AttentionQuestionVO> attention(String token);

    /**
     *  存储用户关注的问题
     */
    void createAttention(QuestionVO questionVO, String token);

    /**
     *  删除我关注的问题
     */
    void deleteAttention(QuestionVO questionVO, String token);
}
