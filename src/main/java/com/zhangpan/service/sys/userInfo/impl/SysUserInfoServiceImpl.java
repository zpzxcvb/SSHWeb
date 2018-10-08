package com.zhangpan.service.sys.userInfo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysUserInfoDao;
import com.zhangpan.model.SysUserInfo;
import com.zhangpan.service.sys.userInfo.SysUserInfoService;

/**
 * @author zhangpan
 * @date 2018年9月28日
 */
@Service
public class SysUserInfoServiceImpl implements SysUserInfoService {

    @Resource
    private SysUserInfoDao userInfoDao;
    
    @Override
    public int save(SysUserInfo model) {
        return userInfoDao.save(model);
    }

    @Override
    public int batchSave(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        return 0;
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return 0;
    }

    @Override
    public int update(SysUserInfo model) {
        return userInfoDao.update(model);
    }

    @Override
    public SysUserInfo findById(Integer id) {
        return null;
    }

    @Override
    public List<SysUserInfo> findList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<Object> findPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public SysUserInfo findByParams(Map<String, Object> params) {
        return userInfoDao.findByParams(params);
    }

}
