package com.zhangpan.service.sys.log.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysLogDao;
import com.zhangpan.model.SysLog;
import com.zhangpan.service.sys.log.SysLogService;

/**
 * @author zhangpan
 * @date 2018年10月29日
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    
    @Resource
    private SysLogDao sysLogDao;

    @Override
    public int save(SysLog model) {
        return sysLogDao.save(model);
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
    public int update(SysLog model) {
        return 0;
    }

    @Override
    public SysLog findById(Integer id) {
        return null;
    }

    @Override
    public List<SysLog> findList(Map<String, Object> params) {
        return null;
    }

    @Override
    public Page<Object> findPage(Map<String, Object> params) {
        return sysLogDao.findPage(params);
    }

}
