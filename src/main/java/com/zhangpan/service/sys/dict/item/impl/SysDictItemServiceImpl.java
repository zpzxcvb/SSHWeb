package com.zhangpan.service.sys.dict.item.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysDictItemDao;
import com.zhangpan.model.SysDictItem;
import com.zhangpan.service.sys.dict.item.SysDictItemService;

/**
 * @author zhangpan
 * @date 2018年9月18日
 */
@Service
public class SysDictItemServiceImpl implements SysDictItemService {
    
    @Resource
    private SysDictItemDao dictItemDao;

    @Override
    public int save(SysDictItem model) {
        return dictItemDao.save(model);
    }

    @Override
    public int deleteById(Integer id) {
        return dictItemDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return dictItemDao.deleteByIds(ids);
    }

    @Override
    public int update(SysDictItem model) {
        return dictItemDao.update(model);
    }

    @Override
    public SysDictItem findById(Integer id) {
        return dictItemDao.findById(id);
    }

    @Override
    public List<SysDictItem> findList(Map<String, String> params) {
        return dictItemDao.findList(params);
    }

    @Override
    public Page<Object> findPage(Map<String, String> params) {
        return dictItemDao.findPage(params);
    }

    @Override
    public int checkItemCode(Map<String, String> params) {
        return dictItemDao.checkItemCode(params);
    }

}
