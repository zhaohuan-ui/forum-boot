package com.forum.modules.questions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.IDUtils.IDUtils;
import com.forum.common.utils.dozer.DozerUtils;
import com.forum.common.utils.redis.RedisUtils;
import com.forum.modules.questions.DO.AnswerDO;
import com.forum.modules.questions.DO.AttentionQuestionDO;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.VO.AnswerVO;
import com.forum.modules.questions.dao.AnswerDao;
import com.forum.modules.questions.dao.AttentionQuestionDao;
import com.forum.modules.questions.dao.QuestionDao;
import com.forum.modules.questions.service.AnswerService;
import com.forum.modules.questions.service.QuestionService;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  业务层: 回答问题查看/评论
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerDao,AnswerDO> implements AnswerService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttentionQuestionDao attentionQuestionDao;

    @Override
    public Map<String,Object> getList(Integer questionId, UserDO userDO) {
        List<AnswerDO> answerDOS = answerDao.selectList(new QueryWrapper<AnswerDO>().
                eq("flag", 0).
                eq("question_id", questionId).
                eq("comment_id", 0).
                orderByDesc("create_time"));
        List<AnswerVO> answerVOList = new ArrayList<>(); // 需要返回的回答
        List<AnswerDO> answerDOList = new ArrayList<>(); // 返回对每个回答的评论
        for (AnswerDO answerDO : answerDOS) {
            answerDOList = answerDao.selectList(new QueryWrapper<AnswerDO>().
                    eq("flag", 0).
                    eq("comment_id", answerDO.getId()));
            AnswerVO answerVO = DozerUtils.map(answerDO, AnswerVO.class);
            // 获取用户昵称 头像地址
            answerVO.setNickName(userService.getById(answerDO.getCreateBy()).getNickName());
            // 获取此回答的评论内容
            answerVO.setComments(DozerUtils.mapList(answerDOList, AnswerVO.class));
            // 返回数据
            answerVOList.add(answerVO);
        }
        // 获取关注者数量
        Integer attentionNumber = attentionQuestionDao.selectList(new QueryWrapper<AttentionQuestionDO>().eq("question_id", questionId).eq("flag", 0)).size();
        // 获取问题信息
        QuestionDO questionDO = questionService.getById(questionId);
        List<AttentionQuestionDO> attentionQuestionDOS = attentionQuestionDao.selectList(
                new QueryWrapper<AttentionQuestionDO>().eq("question_id",questionDO.getId()).eq("flag", 0).eq("create_by", userDO.getId()));
        if(attentionQuestionDOS != null && attentionQuestionDOS.size() > 0){
            questionDO.setAttentionStatus("1");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("questionDO",questionDO);
        map.put("answers",answerVOList);
        map.put("attentionNumber",attentionNumber); // 关注者数量
        map.put("volumeNumber",questionDO.getVolumeNumber()); // 浏览数量
        return map;
    }

    @Override
    public void createAnswer(AnswerVO answerVO, Integer questionId, Integer commentId) {
        AnswerDO answerDO = DozerUtils.map(answerVO, AnswerDO.class);
        answerDO.setId(IDUtils.get10ID());
        answerDO.setQuestionId(questionId);
        answerDO.setCommentId(commentId);
        answerDao.insert(answerDO);
    }
}
