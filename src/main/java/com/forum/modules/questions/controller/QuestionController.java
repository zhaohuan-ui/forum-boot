package com.forum.modules.questions.controller;

import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.service.AttentionQuestionService;
import com.forum.modules.questions.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private AttentionQuestionService attentionQuestionService;

    /**
     *  查询所有人发表的问题
     */
    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    public HttpResult<Object> getList(String token){
        return HttpResultUtil.success("查询成功!",questionService.getList(token));
    }

    /**
     *  写问题 分为发布和稍后发布
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public HttpResult<Object> create(@RequestBody QuestionVO questionVO,String token){
        questionService.create(questionVO, token);
        return HttpResultUtil.success("发布成功!");
    }

    /**
     *  更新浏览量
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public HttpResult<Object> update(@RequestBody QuestionVO questionVO){
        questionService.update(questionVO);
        return HttpResultUtil.success("更新成功!");
    }

    /**
     *  移除草稿箱内容
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public HttpResult<Object> delete(@RequestBody QuestionVO questionVO){
        questionService.delete(questionVO);
        return HttpResultUtil.success("移除成功!");
    }

    /**
     *  查询草稿箱数据
     */
    @RequestMapping(value = "/drafts",method = RequestMethod.POST)
    public HttpResult<Object> drafts(String token){
        return HttpResultUtil.success("查询成功!",questionService.drafts(token));
    }

    /**
     *  稍后答查数据
     */
    @RequestMapping(value = "/later",method = RequestMethod.POST)
    public HttpResult<Object> later(String token){
        return HttpResultUtil.success("查询成功!",questionService.later(token));
    }

    /**
     *  点击稍后答 添加到稍后答列表
     */
    @RequestMapping(value = "/createLater",method = RequestMethod.POST)
    public HttpResult<Object> createLater(@RequestBody QuestionVO questionVO,String token){
        questionService.createLater(questionVO, token);
        return HttpResultUtil.success("添加成功!");
    }

    /**
     *  移除稍后答内容
     */
    @RequestMapping(value = "/deleteLater",method = RequestMethod.DELETE)
    public HttpResult<Object> deleteLater(@RequestBody QuestionVO questionVO,String token){
        questionService.deleteLater(questionVO,token);
        return HttpResultUtil.success("移除成功!");
    }

    /**
     *  我关注的问题数据
     */
    @RequestMapping(value = "/attention",method = RequestMethod.POST)
    public HttpResult<Object> attention(String token){
        return HttpResultUtil.success("查询成功!",attentionQuestionService.attention(token));
    }

    /**
     *  关注问题
     */
    @RequestMapping(value = "/createAttention",method = RequestMethod.POST)
    public HttpResult<Object> createAttention(@RequestBody QuestionVO questionVO,String token){
        attentionQuestionService.createAttention(questionVO, token);
        return HttpResultUtil.success("添加成功!");
    }

    /**
     *  取消我关注的问题
     */
    @RequestMapping(value = "/deleteAttention",method = RequestMethod.DELETE)
    public HttpResult<Object> deleteAttention(@RequestBody QuestionVO questionVO,String token){
        attentionQuestionService.deleteAttention(questionVO,token);
        return HttpResultUtil.success("取消成功!");
    }
}
