/**
 * 
 */
package com.zhangpan.service.sys.menu.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zhangpan.dao.sys.SysMenuDao;
import com.zhangpan.model.SysMenu;
import com.zhangpan.service.sys.menu.SysMenuService;

/**
 * @author zhangpan
 * @date 2018年8月7日
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    
    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public int save(SysMenu model) {
        return sysMenuDao.save(model);
    }

    @Override
    public int deleteById(Integer id) {
        return sysMenuDao.deleteById(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        return sysMenuDao.deleteByIds(ids);
    }

    @Override
    public int update(SysMenu model) {
        return sysMenuDao.update(model);
    }

    @Override
    public SysMenu findById(Integer id) {
        return sysMenuDao.findById(id);
    }

    @Override
    public List<SysMenu> findList(Map<String, String> params) {
        return sysMenuDao.findList(params);
    }

    @Override
    public Page<Object> findPage(Map<String, String> params) {
        return sysMenuDao.findPage(params);
    }

    @Override
    public List<Map<String, Object>> menuList() {
        return sysMenuDao.menuList();
    }

    @Override
    public int hasChild(int id) {
        return sysMenuDao.hasChild(id);
    }

    @Override
    public List<SysMenu> listAllMenu(int id) {
        List<SysMenu> menuList = this.findMenuByPid(id);
        for (SysMenu menu : menuList) {
            menu.setChildList(listAllMenu(menu.getId()));
        }
        return menuList;
    }

    @Override
    public List<SysMenu> findMenuByPid(int id) {
        return sysMenuDao.findMenuByPid(id);
    }
}
