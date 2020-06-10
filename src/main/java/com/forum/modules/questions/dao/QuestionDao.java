package com.forum.modules.questions.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forum.modules.questions.DO.QuestionDO;
import com.forum.modules.questions.DO.UserQuestion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionDao extends BaseMapper<QuestionDO> {

    /**
     *  查询的是稍后答对话框数据
     */
    @Select("select * from user_question where user_id = #{userId}")
    List<UserQuestion> laterList(@Param("userId") Integer userId);

    /**
     *  查询某个问题的关注者是多少
     */
    @Select("select * from user_question where question_id = #{questionId}")
    List<UserQuestion> attentionNumber(@Param("questionId") Integer questionId);

    /**
     *  删除关注问题
     */
    @Delete("delete from user_question where question_id = #{questionId} and user_id = #{userId}")
    void deleteLater(@Param("questionId") Integer questionId, @Param("userId")Integer userId);

    /**
     * 添加关注问题
     */
    @Insert("insert into user_question(id,user_id,question_id) values( #{userQuestion.id}, #{userQuestion.userId},#{userQuestion.questionId})")
    void createLater(@Param("userQuestion") UserQuestion userQuestion);
}
