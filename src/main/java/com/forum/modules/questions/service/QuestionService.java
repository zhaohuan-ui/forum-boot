package com.forum.modules.questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.VO.QuestionVO;

import java.util.List;

public interface QuestionService extends IService<QuestionDO> {

    /**
     *  查询所有已发布问答
     */
    List<QuestionVO> getList(String token);

    /**
     *  写问答
     */
    void create(QuestionVO questionVO,String token);

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
    List<QuestionVO> drafts(String token);

    /**
     *  稍后答数据
     */
    List<QuestionVO> later(String token);

    /**
     *  添加稍后答
     */
    void createLater(QuestionVO questionVO, String token);

    /**
     *  移除稍后答数据
     */
    void deleteLater(QuestionVO questionVO,String token);

}
