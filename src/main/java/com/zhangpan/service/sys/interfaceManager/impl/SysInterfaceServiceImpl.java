package com.zhangpan.service.sys.interfaceManager.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysInterfaceDao;
import com.zhangpan.model.SysInterface;
import com.zhangpan.service.sys.interfaceManager.SysInterfaceService;

@Service
public class SysInterfaceServiceImpl implements SysInterfaceService {

	@Resource
	private SysInterfaceDao sysInterfaceDao;

	@Override
	public int save(SysInterface model) {
		return sysInterfaceDao.save(model);
	}

	@Override
	public int deleteById(Integer id) {
		return sysInterfaceDao.deleteById(id);
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		return sysInterfaceDao.deleteByIds(ids);
	}

	@Override
	public int update(SysInterface model) {
		return sysInterfaceDao.update(model);
	}

	@Override
	public SysInterface findById(Integer id) {
		return sysInterfaceDao.findById(id);
	}

	@Override
	public List<SysInterface> findList(Map<String, Object> params) {
		return sysInterfaceDao.findList(params);
	}

	@Override
	public Page<Object> findPage(Map<String, Object> params) {
		return sysInterfaceDao.findPage(params);
	}

    @Override
    public int batchSave(Map<String, Object> params) {
        return 0;
    }

}
