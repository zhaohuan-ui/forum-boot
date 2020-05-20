package com.forum.modules.article.VO;

import com.forum.modules.article.entity.Article;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleVO {

    
     public Integer id;

    /**
     * 标题
     */
    public String title;

    /**
     * 内容
     */
    public String content;

    /**
     * 浏览量
     */
    public Integer views;

    /**
     * 作者
     */
    public Integer createBy;

    /**
     * 是否可见
     */
    public Integer visible;

    /**
     * 逻辑删除
     */
    public Integer flags;

    /**
     * 创建时间
     */
    public LocalDateTime createTime;

    /**
     * 发布时间
     */
    public Integer releaseTime;

    /**
     * 最新更新时间
     */
    public LocalDateTime updateTime;

    /**
     * 备用字段一
     */
    public String spare1;

    /**
     * 备用字段二
     */
    public String spare2;

    /**
     * 备用字段三
     */
    public Integer spare3;

    /**
     * 返回前端的vo集合
     */
    public List<ArticleVO> articleVOS;

    public ArticleVO(List<Article> list){
        List<ArticleVO> articleList;
        if(list != null && list.size()>0){
            articleList = new ArrayList<>();
            for(Article article : list){
                articleList.add(getArticleVo(article));
            }
        }
    }

    public ArticleVO(){}

    public Article getArticle(ArticleVO articleVo){
        Article article = new Article();
        if(articleVo.title != null){
            article.setTitle(articleVo.title);
        }
        if(articleVo.content != null){
            article.setContent(articleVo.content);
        }
        if(articleVo.views != null){
            article.setViews(articleVo.views);
        }
        if(articleVo.createBy != null){
            article.setCreateBy(articleVo.createBy);
        }
        if(articleVo.visible != null){
            article.setVisible(articleVo.visible);
        }
        if(articleVo.flags != null){
            article.setFlags(articleVo.flags);
        }
        if(articleVo.createTime != null){
            article.setCreateTime(articleVo.createTime);
        }
        if(articleVo.releaseTime != null){
            article.setReleaseTime(articleVo.releaseTime);
        }
        article.setUpdateTime(LocalDateTime.now());

        return article;
    }

    public ArticleVO getArticleVo(Article article){
        if(article.getTitle() != null){
            this.title = article.getTitle();
        }
        if(article.getContent() != null){
            this.content = article.getContent();
        }
        if(article.getViews() != null){
            this.views = article.getViews();
        }
        if(article.getCreateBy() != null){
            this.createBy = article.getCreateBy();
        }
        if(article.getVisible() != null){
            this.visible = article.getVisible();
        }
        if(article.getFlags() != null){
            this.flags = article.getFlags();
        }
        if(article.getCreateTime() != null){
            this.createTime = article.getCreateTime();
        }
        if(article.getReleaseTime() != null){
            this.releaseTime = article.getReleaseTime();
        }
        article.setUpdateTime(LocalDateTime.now());
        return this;
    }
}
