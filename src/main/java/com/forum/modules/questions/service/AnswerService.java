package com.forum.modules.questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.questions.DO.AnswerDO;
import java.util.Map;

public interface AnswerService extends IService<AnswerDO> {

    /**
     *  查询评论数据
     * @param questionId
     * @return
     */
    Map<String,Object> getList(Integer questionId);
}
