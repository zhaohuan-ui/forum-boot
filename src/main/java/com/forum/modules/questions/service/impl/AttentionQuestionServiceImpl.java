package com.forum.modules.questions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.IDUtils.IDUtils;
import com.forum.common.utils.dozer.DozerUtils;
import com.forum.common.utils.redis.RedisUtils;
import com.forum.modules.questions.DO.AttentionQuestionDO;
import com.forum.modules.questions.VO.AttentionQuestionVO;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.dao.AttentionQuestionDao;
import com.forum.modules.questions.service.AttentionQuestionService;
import com.forum.modules.user.DO.UserDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

/**
 *  业务层: 关注问题
 *  @author Mr Zhang
 *  @since 2020-06-09
 */
@Slf4j
@Service
public class AttentionQuestionServiceImpl extends ServiceImpl<AttentionQuestionDao,AttentionQuestionDO> implements AttentionQuestionService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AttentionQuestionDao attentionQuestionDao;

    @Override
    public List<AttentionQuestionVO> attention(UserDO userDO) {
        List<AttentionQuestionDO> attentionQuestionDOS = attentionQuestionDao.selectList(new QueryWrapper<AttentionQuestionDO>().
                eq("flag", 0).
                eq("create_by", userDO.getId()));
        return DozerUtils.mapList(attentionQuestionDOS,AttentionQuestionVO.class);
    }

    @Override
    public void createAttention(QuestionVO questionVO) {
        AttentionQuestionDO attentionQuestionDO = DozerUtils.map(questionVO, AttentionQuestionDO.class);
        attentionQuestionDO.setId(IDUtils.get10ID());
        attentionQuestionDO.setQuestionId(questionVO.getId());
        attentionQuestionDao.insert(attentionQuestionDO);
        log.info("[ create ] " + attentionQuestionDO);
    }

    @Override
    public void deleteAttention(QuestionVO questionVO, UserDO userDO) {
        int delete = attentionQuestionDao.delete(new QueryWrapper<AttentionQuestionDO>().
                eq("question_name", questionVO.getQuestionName()).
                eq("create_by", userDO.getId()));
        log.info("[ delete ] " + questionVO.toString());
    }
}
