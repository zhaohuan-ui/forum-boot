package com.forum.modules.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forum.modules.article.entity.Article;

/**
 * <p>
 * 文章表 服务类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
public interface ArticleService extends IService<Article> {

    //删除文章 将flag改为1
    String updateFlags(Integer id);
}
