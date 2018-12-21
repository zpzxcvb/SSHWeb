package com.zhangpan.service.article.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.article.ArticleDao;
import com.zhangpan.model.article.Article;
import com.zhangpan.service.article.ArticleService;

/**
 * @author zhangpan
 * @date 2018年11月19日
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    
    @Autowired
    private ArticleDao articleDao;

    @Override
    public int save(Article model) {
        return articleDao.save(model);
    }

    @Override
    public int batchSave(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return articleDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return articleDao.deleteByIds(ids);
    }

    @Override
    public int update(Article model) {
        return articleDao.update(model);
    }

    @Override
    public Article findById(Integer id) {
        return articleDao.findById(id);
    }

    @Override
    public List<Article> findList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<Object> findPage(Map<String, Object> params) {
        return articleDao.findPage(params);
    }

    @Override
    public Object preview(Integer id) {
        return articleDao.preview(id);
    }

}
