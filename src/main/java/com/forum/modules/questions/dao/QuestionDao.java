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
     *  查询是否点击稍后答按钮
     * @param userId
     * @param questionId
     * @return
     */
    @Select("select * from user_question where user_id = #{userId} and question_id = #{questionId}")
    List<UserQuestion> later(@Param("userId")Integer userId, @Param("questionId") Integer questionId);

    /**
     *  查询的是稍后答对话框数据
     */
    @Select("select * from user_question where user_id = #{userId}")
    List<UserQuestion> laterList(@Param("userId") Integer userId);

    @Delete("delete from user_question where user_id = #{userId} and question_id = #{questionId}")
    void deleteLater(@Param("userId")Integer userId, @Param("questionId") Integer questionId);

    @Insert("insert into user_question(id,user_id,question_id) values( #{userQuestion.id}, #{userQuestion.userId},#{userQuestion.questionId})")
    void createLater(@Param("userQuestion") UserQuestion userQuestion);
}
