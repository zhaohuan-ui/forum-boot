package com.forum.modules.questions.controller;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
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

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public HttpResult<Object> getList(Integer questionId){
        return HttpResultUtil.success("查询成功!",answerService.getList(questionId));
    }
}
