package com.forum.modules.questions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.IDUtils.IDUtils;
import com.forum.common.utils.dozer.DozerUtils;
import com.forum.common.utils.redis.RedisUtils;
import com.forum.modules.questions.DO.AttentionQuestionDO;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.DO.UserQuestion;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.dao.AttentionQuestionDao;
import com.forum.modules.questions.dao.QuestionDao;
import com.forum.modules.questions.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao,QuestionDO> implements QuestionService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private AttentionQuestionDao attentionQuestionDao;

    @Override
    public List<QuestionVO> getList(String token) {
        List<QuestionDO> questionDOS = questionDao.selectList(
                new QueryWrapper<QuestionDO>()
                .eq("flag",0)
                .eq("publish_status",0)
                .orderByDesc("create_time"));
        for (QuestionDO questionDO : questionDOS) {
            List<UserQuestion> userQuestions = questionDao.later(Integer.valueOf(redisUtils.get(token)), questionDO.getId());
            if(userQuestions != null && userQuestions.size()>0){
                questionDO.setLaterNumber(1);
            }
            List<AttentionQuestionDO> attentionQuestionDOS = attentionQuestionDao.selectList(new QueryWrapper<AttentionQuestionDO>().
                    eq("question_name", questionDO.getQuestionName()).
                    eq("create_by", Integer.valueOf(redisUtils.get(token))));
            if(attentionQuestionDOS != null && attentionQuestionDOS.size()>0){
                questionDO.setAttentionNumber(1);
            }
        }
        log.info("查询问答所有数据==>"+questionDOS.size()+"条!");
        return DozerUtils.mapList(questionDOS, QuestionVO.class);
    }

    @Override
    public void create(QuestionVO questionVO,String token) {
        QuestionDO questionDO = DozerUtils.map(questionVO, QuestionDO.class);
        questionDO.setId(IDUtils.getId());
        questionDO.setCreateBy(Integer.valueOf(redisUtils.get(token)));
        this.save(questionDO);
    }

    @Override
    public void update(QuestionVO questionVO) {
        QuestionDO questionDO = DozerUtils.map(questionVO, QuestionDO.class);
        questionDao.updateById(questionDO);
    }

    @Override
    public void delete(QuestionVO questionVO) {
        questionDao.deleteById(questionVO.getId());
    }

    @Override
    public List<QuestionVO> drafts(String token) {
        List<QuestionDO> questionDOS = questionDao.selectList(
                new QueryWrapper<QuestionDO>()
                        .eq("flag",0)
                        .eq("publish_status",1)
                        .eq("create_by",Integer.valueOf(redisUtils.get(token)))
                        .orderByDesc("create_time"));
        log.info("查询草稿箱数据==>"+questionDOS.size()+"条!");
        return DozerUtils.mapList(questionDOS, QuestionVO.class);
    }

    @Override
    public List<QuestionVO> later(String token) {
        List<UserQuestion> userQuestions = questionDao.laterList(Integer.valueOf(redisUtils.get(token)));
        List<QuestionDO> questionDOS = new ArrayList<>();
        for (UserQuestion userQuestion : userQuestions) {
            questionDOS.add(questionDao.selectById(userQuestion.getQuestionId()));
        }
        log.info("查询稍后答数据==>"+questionDOS.size()+"条!");
        return DozerUtils.mapList(questionDOS, QuestionVO.class);
    }

    @Override
    public void createLater(QuestionVO questionVO, String token) {
        Integer userId = Integer.valueOf(redisUtils.get(token));
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setId(IDUtils.getId());
        userQuestion.setUserId(userId);
        userQuestion.setQuestionId(questionVO.getId());
        questionDao.createLater(userQuestion);
    }

    @Override
    public void deleteLater(QuestionVO questionVO,String token) {
        questionDao.deleteLater(Integer.valueOf(redisUtils.get(token)),questionVO.getId());
    }

}
