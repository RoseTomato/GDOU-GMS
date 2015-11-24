package love.drose.gms.controllers;

import love.drose.gms.models.Privilege;
import love.drose.gms.services.PrivilegeService;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/24.
 */
@Controller
@RequestMapping("/privilegeHandler")
public class PrivilegeHandler {

    Logger logger = LogManager.getLogger(PrivilegeHandler.class);

    private final String LIST_PRIVILEGE = "org/listPrivilege";
    private final String FORWARD_UPDATE_PRIVILEGE = "org/updatePrivilege";

    @Autowired
    private PrivilegeService privilegeService;

    Map<String,String> result = null;

    @ResponseBody
    @RequestMapping(value = "/addPrivilege", method = RequestMethod.POST)
    public Object addPrivilege(Privilege privilege) {
        logger.debug("<== [privilege:" + privilege + "]");
        try {
            if (privilege != null) {
                privilegeService.save(privilege);
                result = DWZJsonUtil.successAndRefresh("showPrivileges");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }

        logger.debug("==>");
        return result;
    }

    @RequestMapping(value = "/listPrivilege", method = RequestMethod.GET)
    public ModelAndView listPrivilege(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize: " + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> data = null;
        try {
            // 获取系统权限分页数据
            Page page = privilegeService.findPageDataWhereIsNull(pageNum, pageSize, "roleId");
            modelAndView.addObject("page", page);
            modelAndView.setViewName(LIST_PRIVILEGE);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName("404");
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("/forwardUpdatePrivilege")
    public ModelAndView forwardUpdatePrivilege(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> data = null;
        try {
            // 获取权限模型
            Privilege privilege = privilegeService.findById(id);
            modelAndView.addObject("privilege", privilege);
            modelAndView.setViewName(FORWARD_UPDATE_PRIVILEGE);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName("404");
            e.printStackTrace();
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/updatePrivilege", method = RequestMethod.POST)
    public Object updatePrivilege(Privilege privilege) {
        logger.debug("<== [privilege:" + privilege + "]");
        try {
            if (privilege != null) {
                // 先获取原始的权限名
                String oldName = privilegeService.findById(privilege.getId()).getName();
                // 根据名字找出全部权限
                List<Privilege> privileges = privilegeService.findAllByName(oldName);

                // 逐个更新
                if (null != privileges) {
                    for (Privilege oldPrivilege : privileges) {
                        // 若是已经绑定了角色id的，要替换为原来的id和roleId
                        if (oldPrivilege.getRoleId() != null) {
                            privilege.setId(oldPrivilege.getId());
                            privilege.setRoleId(oldPrivilege.getRoleId());
                        }
                        privilegeService.update(privilege);
                    }
                }

                result = DWZJsonUtil.successAndRefresh("showPrivileges");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/deletePrivilege")
    public Object deletePrivilege(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            // 获取权限名
            String name = privilegeService.findById(id).getName();
            if (name != null) {
                // 根据权限名删除
                privilegeService.deleteByField("name", name);
            }
            result = DWZJsonUtil.successAndRefresh("showPrivileges");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙..");
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }
}
