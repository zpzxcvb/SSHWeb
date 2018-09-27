package com.zhangpan.service.sys.dict.type.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysDictTypeDao;
import com.zhangpan.model.SysDictType;
import com.zhangpan.service.sys.dict.type.SysDictTypeService;

/**
 * @author zhangpan
 * @date 2018年9月18日
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {
    
    @Resource
    private SysDictTypeDao dictTypeDao;

    @Override
    public int save(SysDictType model) {
        return dictTypeDao.save(model);
    }

    @Override
    public int deleteById(Integer id) {
        return dictTypeDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return dictTypeDao.deleteByIds(ids);
    }

    @Override
    public int update(SysDictType model) {
        return dictTypeDao.update(model);
    }

    @Override
    public SysDictType findById(Integer id) {
        return dictTypeDao.findById(id);
    }

    @Override
    public List<SysDictType> findList(Map<String, String> params) {
        return dictTypeDao.findList(params);
    }

    @Override
    public Page<Object> findPage(Map<String, String> params) {
        return dictTypeDao.findPage(params);
    }

    @Override
    public List<Map<String, Object>> findDictTypes(Map<String, String> params) {
        return dictTypeDao.findDictTypes(params);
    }

    @Override
    public int checkTypeCode(Map<String, String> params) {
        return dictTypeDao.checkTypeCode(params);
    }

}
