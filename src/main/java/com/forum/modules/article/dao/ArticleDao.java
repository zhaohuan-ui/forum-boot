package com.forum.modules.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forum.modules.article.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
public interface ArticleDao extends BaseMapper<Article> {

    @Select("select * from a")
    void getOne(@Param("id") Integer id);

}
