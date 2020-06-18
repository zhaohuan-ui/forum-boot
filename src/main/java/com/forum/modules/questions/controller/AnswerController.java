package com.forum.modules.questions.controller;

import com.forum.common.annotation.LoginUser;
import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.questions.VO.AnswerVO;
import com.forum.modules.questions.service.AnswerService;
import com.forum.modules.user.DO.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器: 回答问题查看/评论
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
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
    public HttpResult<Object> getList(Integer questionId, @LoginUser UserDO userDO){
        return HttpResultUtil.success("查询成功!",answerService.getList(questionId, userDO));
    }

    /**
     *  回答问题 分为回答问题和评论已回答问题
     */
    @RequestMapping(value = "/createAnswer",method = RequestMethod.POST)
    public HttpResult<Object> createAnswer(@RequestBody AnswerVO answerVO, Integer questionId, Integer commentId, @LoginUser UserDO userDO){
        answerService.createAnswer(answerVO, questionId, commentId,userDO);
        return HttpResultUtil.success("回答成功!");
    }
}
