package com.zhangpan.model;

import java.io.Serializable;

/**
 * 菜单表 "sys_menu"
 * @author zhangpan
 * @date 2018年8月7日
 */
public class SysMenu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String menuName;

    private String url;

    private Integer pid;
    
    private Integer orderNo;
    
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}