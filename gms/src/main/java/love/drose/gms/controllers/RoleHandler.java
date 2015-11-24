package love.drose.gms.controllers;

import love.drose.gms.models.Privilege;
import love.drose.gms.models.Role;
import love.drose.gms.services.ManagerService;
import love.drose.gms.services.PrivilegeService;
import love.drose.gms.services.RoleService;
import love.drose.gms.utils.DWZJsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 角色控制类.
 * Created by lovedrose on 2015/11/18.
 */
@Controller
@RequestMapping("/roleHandler")
public class RoleHandler {

    Logger logger = LogManager.getLogger(RoleHandler.class);

    private final String LIST_ROLE = "org/listRole";
    private final String DETAIL_ROLE = "org/detailRole";
    private final String FORWARD_ASSOCIATE_PRIVILEGE = "org/associatePrivilege";

    @Autowired
    private RoleService roleService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PrivilegeService privilegeService;

    Map<String, String> result = null;

    /**
     * 添加角色
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRole")
    public Object addRole(Role role) {
        logger.debug("<== [role:" + role + "]");

        try {
            roleService.save(role);
            result = DWZJsonUtil.successAndRefresh("showRoles");
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
        }

        logger.debug("==>");
        return result;
    }

    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/listRole")
    public ModelAndView listRole() {
        logger.debug("<==");
        Map<String, Object> data = null;
        try {
            // 找出系统角色分页数据
            List<Role> roles = roleService.findRoles();
            data = new HashMap<String, Object>();
            data.put("roles", roles);

            return new ModelAndView(LIST_ROLE,data);
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
        }

        logger.debug("==>");
        return null;
    }

    @RequestMapping("/detailRole")
    public ModelAndView detailRole(String name) {
        logger.debug("<== [name:" + name + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 根据名字找出Role
            Role role = roleService.findByName(name);

            // 根据名字找到关联的管理员id集合
            List<Integer> managerIds = roleService.findManagerIdsByRoleName(name);
            // 找出对应的管理员名字
            List<String> managerNames = managerService.findNamesByIds(managerIds);

            // 根据角色id找出其所拥有的权限
            Collection<String> privilegeNames = privilegeService.findPrivilegesOfRoleById(role.getId());

            modelAndView.addObject("role", role);
            modelAndView.addObject("managerNames", managerNames);
            modelAndView.addObject("privilegeNames", privilegeNames);
            modelAndView.setViewName(DETAIL_ROLE);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName("404");
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/updateRole")
    public Object updateRole(Role role) {
        logger.debug("<== [role:" + role + "]");
        try {
            if (role != null) {
                // 先获取原始的管理员
                String oldName = roleService.findById(role.getId()).getName();
                // 根据名字找出全部角色
                List<Role> roles = roleService.findAllByName(oldName);

                // 逐个更新
                if (null != roles) {
                    for (Role oldRole : roles) {
                        // 若是已经绑定了管理员id的，要替换为原来的id和managerId
                        if (oldRole.getManagerId() != null) {
                            role.setId(oldRole.getId());
                            role.setManagerId(oldRole.getManagerId());
                        }
                        roleService.update(role);
                    }
                }

                result = DWZJsonUtil.successAndRefresh("showPrivileges");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统繁忙..");
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteRole")
    public Object deleteRole(String name) {
        logger.debug("<== [name:" + name + "]");
        try {
            Role role = roleService.findByName(name);
            // 删除权限关联id的数据
            privilegeService.deleteByRoleId(role.getId());

            // 根据名字删除角色
            roleService.deleteByField("name", name);
            result = DWZJsonUtil.successAndRefresh("showRoles");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/forwardAssociatePrivilege")
    public ModelAndView forwardAssociatePrivilege(String name) {
        logger.debug("<== [name:" + name + "]");
        Map<String, Object> data = null;
        try {
            // 查出角色
            Role role = roleService.findByName(name);
            // 查出系统角色
            List<Privilege> privileges = privilegeService.findPrivileges();

            // 除去角色已有的权限
            Collection<String> privilegeNames = privilegeService.findPrivilegesOfRoleById(role.getId());
            if (null != privilegeNames && privilegeNames.size() > 0) {
                for(String privilegeName : privilegeNames) {
                    for(int i = 0; i < privileges.size();i++) {
                        if (privilegeName.equals(privileges.get(i).getName())) {
                            privileges.remove(i);
                        }
                    }
                }
            }

            // 将管理员id和角色集合传到页面
            data = new HashMap<String, Object>();
            data.put("roleId", role.getId());
            data.put("privileges", privileges);

            logger.debug("==>");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        return new ModelAndView(FORWARD_ASSOCIATE_PRIVILEGE, data);
    }

    @ResponseBody
    @RequestMapping("/associatePrivilege")
    public Object associatePrivilege(Integer roleId, Integer[] privilegeIds) {
        logger.debug("<== [roleId:" + roleId + ", privilegeIds.length:" + privilegeIds.length + "]");
        try {
            // 给角色关联权限
            if (null != privilegeIds && privilegeIds.length > 0 && roleId != null) {
                for (Integer privilegeId : privilegeIds) {
                    roleService.associatePrivilegeById(roleId, privilegeId);
                }
                result = DWZJsonUtil.successAndRefresh("showManagers");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }
}
