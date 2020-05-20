package com.forum.modules.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forum.common.utils.result.HttpResult;
import com.forum.common.utils.result.HttpResultUtil;
import com.forum.modules.article.VO.ArticleVO;
import com.forum.modules.article.entity.Article;
import com.forum.modules.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author zhaohuan
 * @since 2020-05-03
 */
//TODO
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/save")
    public HttpResult<Object> save(@RequestBody ArticleVO articleVo){
       articleService.save(articleVo.getArticle(articleVo));
       return HttpResultUtil.success("message",articleVo);
    }

    @RequestMapping("/delete")
    public HttpResult<Object> delete(@RequestParam Integer id){
        return HttpResultUtil.success("message",articleService.updateFlags(id));
    }

    @RequestMapping("/update")
    public HttpResult<Object> update(@RequestParam ArticleVO articleVo){
        return HttpResultUtil.success("message",articleService.updateById(articleVo.getArticle(articleVo)));
    }

    @RequestMapping("/list")
    public HttpResult<Object> list(@RequestBody ArticleVO articleVo){
        return HttpResultUtil.success("message",articleService.list(new QueryWrapper<Article>().eq("create_by",articleVo.createBy)));
    }


}
