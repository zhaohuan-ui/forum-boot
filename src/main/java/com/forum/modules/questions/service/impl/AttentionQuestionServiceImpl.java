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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AttentionQuestionServiceImpl extends ServiceImpl<AttentionQuestionDao,AttentionQuestionDO> implements AttentionQuestionService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AttentionQuestionDao attentionQuestionDao;

    @Override
    public List<AttentionQuestionVO> attention(String token) {
        List<AttentionQuestionDO> attentionQuestionDOS = attentionQuestionDao.selectList(new QueryWrapper<AttentionQuestionDO>().
                eq("flag", 0).
                eq("create_by", Integer.valueOf(redisUtils.get(token))));
        return DozerUtils.mapList(attentionQuestionDOS,AttentionQuestionVO.class);
    }

    @Override
    public void createAttention(QuestionVO questionVO, String token) {
        AttentionQuestionDO attentionQuestionDO = DozerUtils.map(questionVO, AttentionQuestionDO.class);
        attentionQuestionDO.setId(IDUtils.get10ID());
        attentionQuestionDO.setCreateBy(Integer.valueOf(redisUtils.get(token))); // 创建人ID
        attentionQuestionDao.insert(attentionQuestionDO);
    }

    @Override
    public void deleteAttention(QuestionVO questionVO, String token) {
        int delete = attentionQuestionDao.delete(new QueryWrapper<AttentionQuestionDO>().
                eq("question_name", questionVO.getQuestionName()).
                eq("create_by", Integer.valueOf(redisUtils.get(token))));
        log.info("移除我关注的问题数据==>"+delete);
    }
}
