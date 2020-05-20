package com.forum.modules.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forum.common.utils.date.DateUtils;
import com.forum.modules.article.entity.Article;
import com.forum.modules.article.dao.ArticleDao;
import com.forum.modules.article.service.ArticleService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Override
    public String updateFlags(Integer id) {
        if(this.updateById(getArticle().setId(id).setFlags(1))){
            return "success";
        }
        return "error";
    }

    /**
     * 物理删除
     * @return
     */
    //TODO
    public void deleteByFlags(){
        this.removeByIds(this.list(new QueryWrapper<Article>().eq("flags",1).gt("release_time",DateUtils.getCurrentTimestamp(0)-0x18b81ff)));
    }

    public Article getArticle(){return new Article();}
}
