package com.zhangpan.service.article;

import com.zhangpan.model.article.Article;
import com.zhangpan.service.BaseService;

/**
 * @author zhangpan
 * @date 2018年11月19日
 */
public interface ArticleService extends BaseService<Article, Integer> {
    /**
     * 预览
     * @param id
     * @return
     */
    public Object preview(Integer id);
}
