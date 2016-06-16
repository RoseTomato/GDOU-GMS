package love.drose.gms.shiro;

import love.drose.gms.models.Manager;
import love.drose.gms.models.Role;
import love.drose.gms.services.ManagerService;
import love.drose.gms.services.PrivilegeService;
import love.drose.gms.services.RoleService;
import love.drose.gms.utils.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Administrator on 2015/11/20.
 */
@Service
public class MyShiro extends AuthorizingRealm {

    Logger logger = (Logger) LogManager.getLogger(MyShiro.class);

    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("in <== [principals:" + principals + "]");

        // 获取登陆时输入的用户名
        String loginName = (String) principals.fromRealm(getName()).iterator().next();
        // 到数据库中查询是否有此对象
        Manager manager = managerService.findByUsername(loginName);

        if (manager != null) {
            // 权限信息对象info，用来存放查出的用户所有的角色及权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 用户角色集合
            // 测试：假设admin是超级管理员
            Set<String> roles = managerService.findRolesOfManagerById(manager.getId());
            info.setRoles(roles);
//            添加用户所有的权限，如果只使用角色定义访问权限，下面4行可以不要
//            for (String roleName : roles) {
//                Role role = roleService.findByName(roleName);
//                info.addStringPermissions(privilegeService.findPrivilegesOfRoleById(role.getId()));
//            }

            logger.debug("out ==>");
            return info;
        }
        logger.debug("out ==> null");
        return null;
    }

    /**
     * 登陆认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String password = String.valueOf(((UsernamePasswordToken) token).getPassword());
        logger.debug("in ==> [Principal:"+token.getPrincipal()+", password:" + password + "]");
        // UsernamePasswordToken 对象用来存放提交的登陆信息
        UsernamePasswordToken upt = (UsernamePasswordToken) token;

        // 获取登陆时输入的密码，并使用md5算法进行加密，在赋值给token的password属性
        String md5Password = MD5Util.getMD5String(password);
        ((UsernamePasswordToken) token).setPassword(md5Password.toCharArray());

        // 查出是否有此管理员
        Manager manager = managerService.findByUsername(upt.getUsername());

        if(manager != null) {
            logger.debug("out ==>");
            return new SimpleAuthenticationInfo(manager.getUsername(), manager.getPassword(), getName());
        }

        logger.debug("out ==> null");
        return null;
    }
}
