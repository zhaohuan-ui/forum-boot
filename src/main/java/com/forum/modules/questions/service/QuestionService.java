package com.forum.modules.questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.VO.QuestionVO;
import com.forum.modules.user.DO.UserDO;

import java.util.List;

public interface QuestionService extends IService<QuestionDO> {

    /**
     *  查询所有已发布问答
     */
    List<QuestionVO> getList(UserDO userDO);

    /**
     *  写问答
     */
    void create(QuestionVO questionVO);

    /**
     *  更新问答 包括 浏览量 草稿箱
     */
    void update(QuestionVO questionVO);

    /**
     *  删除问答
     */
    void delete(QuestionVO questionVO);

    /**
     *  查询草稿箱数据
     */
    List<QuestionVO> drafts(UserDO userDO);

    /**
     *  稍后答数据
     */
    List<QuestionVO> later(UserDO userDO);

    /**
     *  添加稍后答
     */
    void createLater(QuestionVO questionVO);

    /**
     *  移除稍后答数据
     */
    void deleteLater(QuestionVO questionVO, UserDO userDO);

}
