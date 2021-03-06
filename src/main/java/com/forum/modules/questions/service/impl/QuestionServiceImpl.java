package com.forum.modules.questions.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.IDUtils.IDUtils;
import com.forum.common.utils.PageUtils;
import com.forum.common.utils.dozer.DozerUtils;
import com.forum.common.utils.redis.RedisUtils;
import com.forum.modules.questions.DO.AnswerDO;
import com.forum.modules.questions.DO.AttentionQuestionDO;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.DO.UserQuestion;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.questions.dao.AnswerDao;
import com.forum.modules.questions.dao.AttentionQuestionDao;
import com.forum.modules.questions.dao.QuestionDao;
import com.forum.modules.questions.service.AnswerService;
import com.forum.modules.questions.service.AttentionQuestionService;
import com.forum.modules.questions.service.QuestionService;
import com.forum.modules.user.DO.UserDO;
import com.forum.modules.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务层: 问题查看/发表
 *
 * @author Mr Zhang
 * @since 2020-06-09
 */
@Slf4j
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, QuestionDO> implements QuestionService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private AttentionQuestionService attentionQuestionService;

    @Override
    public List<QuestionVO> getList(PageUtils pageUtils, UserDO userDO) {
        QueryWrapper<QuestionDO> queryWrapper = new QueryWrapper<QuestionDO>()
                .eq("flag", 0)
                .eq("publish_status", 0)
                .orderByDesc("create_time");
        Page<QuestionDO> page = new Page<>(pageUtils.getCurrent(), pageUtils.getSize());
        IPage<QuestionDO> iPage = baseMapper.selectPage(page, queryWrapper);
        List<QuestionDO> questionDOS = iPage.getRecords();
        List<AttentionQuestionDO> attentionQuestionDOS = attentionQuestionService.list(
                new QueryWrapper<AttentionQuestionDO>()
                        .eq("flag", 0)
                        .eq("create_by", userDO.getId()));
        // 该用户稍后答集合
        List<UserQuestion> userQuestions = baseMapper.laterList(userDO.getId());
        // 遍历问题
        for (QuestionDO questionDO : questionDOS) {
            for (AttentionQuestionDO attentionQuestionDO : attentionQuestionDOS) {
                // 判断这个问题是否被此用户添加到关注
                if (attentionQuestionDO.getQuestionId().intValue() == questionDO.getId()) {
                    // 添加到关注后即改变状态值
                    questionDO.setAttentionStatus("1");
                }
            }
            for (UserQuestion userQuestion : userQuestions) {
                // 判断这个问题是否被此用户添加到稍后答
                if (userQuestion.getQuestionId().intValue() == questionDO.getId()) {
                    // 添加到稍后答 即改变状态值
                    questionDO.setLaterStatus("1");
                }
            }
            List<AnswerDO> answerDOList = answerDao.selectList(
                    new QueryWrapper<AnswerDO>()
                            .eq("flag", 0).eq("question_id", questionDO.getId()));
            questionDO.setAnswerNumber(answerDOList.size());
        }
        return DozerUtils.mapList(questionDOS, QuestionVO.class);
    }

    @Override
    public void create(QuestionVO questionVO) {
        QuestionDO questionDO = DozerUtils.map(questionVO, QuestionDO.class);
        questionDO.setId(IDUtils.get10ID());
        this.save(questionDO);
    }

    @Override
    public void update(QuestionVO questionVO) {
        QuestionDO questionDO = DozerUtils.map(questionVO, QuestionDO.class);
        questionDO.setLaterStatus("0");
        questionDO.setAttentionStatus("0");
        baseMapper.updateById(questionDO);
    }

    @Override
    public void delete(QuestionVO questionVO) {
        baseMapper.deleteById(questionVO.getId());
    }

    @Override
    public List<QuestionVO> drafts(UserDO userDO) {
        List<QuestionDO> questionDOS = baseMapper.selectList(
                new QueryWrapper<QuestionDO>()
                        .eq("flag", 0)
                        .eq("publish_status", 1)
                        .eq("create_by", userDO.getId())
                        .orderByDesc("create_time"));
        return DozerUtils.mapList(questionDOS, QuestionVO.class);
    }

    @Override
    public List<QuestionVO> later(UserDO userDO) {
        // 查询此用户添加的所有稍后答
        List<UserQuestion> userQuestions = baseMapper.laterList(userDO.getId());
        List<QuestionDO> questions = new ArrayList<>();
        // 遍历稍后答 根据问题id查询问题信息存储后返回
        for (UserQuestion userQuestion : userQuestions) {
            questions.add(baseMapper.selectById(userQuestion.getQuestionId()));
        }
        return DozerUtils.mapList(questions, QuestionVO.class);
    }

    @Override
    public void createLater(QuestionVO questionVO, UserDO userDO) {
        try {
            // 创建稍后答对象 (由于没有使用BaseDO,即无法自动注入创建人ID,需要手动添加)
            UserQuestion userQuestion = new UserQuestion();
            userQuestion.setId(IDUtils.get10ID());
            userQuestion.setUserId(userDO.getId());
            userQuestion.setQuestionId(questionVO.getId());
            baseMapper.createLater(userQuestion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLater(QuestionVO questionVO, UserDO userDO) {
        baseMapper.deleteLater(questionVO.getId(), userDO.getId());
    }

}
