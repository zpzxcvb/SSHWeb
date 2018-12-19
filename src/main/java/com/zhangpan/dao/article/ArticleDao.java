package com.zhangpan.dao.article;

import com.zhangpan.dao.BaseDao;
import com.zhangpan.model.article.Article;

public interface ArticleDao extends BaseDao<Article,Integer> {
    
    /**
     * 预览
     * @param id
     * @return
     */
    public Object preview(Integer id);
    
}