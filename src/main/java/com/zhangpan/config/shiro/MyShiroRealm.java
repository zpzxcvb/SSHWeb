package com.zhangpan.config.shiro;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangpan.model.SysMenu;
import com.zhangpan.model.SysRole;
import com.zhangpan.model.SysUser;
import com.zhangpan.service.sys.role.SysRoleService;
import com.zhangpan.service.sys.user.SysUserService;

/**
 * @author zhangpan
 * @date 2018年10月11日
 */
public class MyShiroRealm extends AuthorizingRealm {
    
    @Autowired
    private SysUserService userService;
    
    @Autowired
    private SysRoleService roleService;

    /**
     * 用户认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进入用户登录");
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //获取用户信息
        String name = token.getUsername();
        
        SysUser user = userService.findUserByName(name);
        
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            if(user.getStatus() == 0) {
                throw new LockedAccountException(); 
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
    }
    
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入角色授权");
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        
        List<SysRole> roles = userService.findRoleByUserName(name);
        
        for (SysRole role : roles) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            
            List<Map<String, Object>> permissions = roleService.findPermissionByRoleId(role.getRoleId());
            
            for (Map<String, Object> permission : permissions) {
                //添加权限
                simpleAuthorizationInfo.addStringPermission(permission.get("name").toString());
            }
        }
        return simpleAuthorizationInfo;
    }

}
