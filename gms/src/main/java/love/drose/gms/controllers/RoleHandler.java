package love.drose.gms.controllers;

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
                roleService.update(role);
                result = DWZJsonUtil.closeCurrentAndRefresh("showRoles");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统繁忙..");
        }
        logger.debug("==>");
        return result;
    }
}
