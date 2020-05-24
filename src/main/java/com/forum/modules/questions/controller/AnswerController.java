package com.forum.modules.questions.controller;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.questions.VO.AnswerVO;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.service.AnswerService;
import com.forum.modules.questions.service.AttentionQuestionService;
import com.forum.modules.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    /**
     *  查询此问题所有回答 以及回答以下的评论
     * @param questionId
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public HttpResult<Object> getList(Integer questionId){
        return HttpResultUtil.success("查询成功!",answerService.getList(questionId));
    }

    /**
     *  回答问题 分为回答问题和评论已回答问题
     */
    @RequestMapping(value = "/createAnswer",method = RequestMethod.POST)
    public HttpResult<Object> createAnswer(@RequestBody AnswerVO answerVO, Integer querstionId, Integer commentId, String token){
        answerService.createAnswer(answerVO, querstionId, commentId, token);
        return HttpResultUtil.success("回答成功!");
    }
}
