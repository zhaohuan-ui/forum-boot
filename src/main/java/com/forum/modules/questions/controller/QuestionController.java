package com.forum.modules.questions.controller;

import com.forum.common.annotation.LoginUser;
import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.service.AttentionQuestionService;
import com.forum.modules.questions.service.QuestionService;
import com.forum.modules.user.DO.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  控制器: 问题查看/发表
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
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
    public HttpResult<Object> getList(@LoginUser UserDO userDO){
        return HttpResultUtil.success("查询成功!",questionService.getList(userDO));
    }

    /**
     *  写问题 分为发布和稍后发布
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public HttpResult<Object> create(@RequestBody QuestionVO questionVO){
        questionService.create(questionVO);
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
     *  查询草稿箱数据
     */
    @RequestMapping(value = "/drafts",method = RequestMethod.POST)
    public HttpResult<Object> drafts(@LoginUser UserDO userDO){
        return HttpResultUtil.success("查询成功!",questionService.drafts(userDO));
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
     *  稍后答查数据
     */
    @RequestMapping(value = "/later",method = RequestMethod.POST)
    public HttpResult<Object> later(@LoginUser UserDO userDO){
        return HttpResultUtil.success("查询成功!",questionService.later(userDO));
    }

    /**
     *  点击稍后答 添加到稍后答列表
     */
    @RequestMapping(value = "/createLater",method = RequestMethod.POST)
    public HttpResult<Object> createLater(@RequestBody QuestionVO questionVO, @LoginUser UserDO userDO){
        questionService.createLater(questionVO, userDO);
        return HttpResultUtil.success("添加成功!");
    }

    /**
     *  移除稍后答内容
     */
    @RequestMapping(value = "/deleteLater",method = RequestMethod.DELETE)
    public HttpResult<Object> deleteLater(@RequestBody QuestionVO questionVO, @LoginUser UserDO userDO){
        questionService.deleteLater(questionVO, userDO);
        return HttpResultUtil.success("移除成功!");
    }

    /**
     *  我关注的问题数据
     */
    @RequestMapping(value = "/attention",method = RequestMethod.POST)
    public HttpResult<Object> attention(@LoginUser UserDO userDO){
            return HttpResultUtil.success("查询成功!",attentionQuestionService.attention(userDO));
    }

    /**
     *  关注问题
     */
    @RequestMapping(value = "/createAttention",method = RequestMethod.POST)
    public HttpResult<Object> createAttention(@RequestBody QuestionVO questionVO){
        attentionQuestionService.createAttention(questionVO);
        return HttpResultUtil.success("添加成功!");
    }

    /**
     *  取消我关注的问题
     */
    @RequestMapping(value = "/deleteAttention",method = RequestMethod.DELETE)
    public HttpResult<Object> deleteAttention(@RequestBody QuestionVO questionVO, @LoginUser UserDO userDO){
        attentionQuestionService.deleteAttention(questionVO,userDO);
        return HttpResultUtil.success("取消成功!");
    }
}
