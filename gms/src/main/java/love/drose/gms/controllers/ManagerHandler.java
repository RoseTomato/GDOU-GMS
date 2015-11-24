package love.drose.gms.controllers;

import love.drose.gms.models.Manager;
import love.drose.gms.models.Role;
import love.drose.gms.services.ManagerService;
import love.drose.gms.services.RoleService;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.MD5Util;
import love.drose.gms.utils.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制类
 * Created by lovedrose on 2015/11/18.
 */
@Controller
@RequestMapping("/managerHandler")
public class ManagerHandler {

    private static Logger logger = LogManager.getLogger(ManagerHandler.class.getName());

    @Autowired
    private RoleService roleService;

    @Autowired
    private ManagerService managerService;

    private final String FORWARD_ADD_MANAGER = "org/addManager";
    private final String FORWARD_LIST_MANAGER = "org/listManager";
    private final String FORWARD_DETAIL_MANAGER = "org/detailManager";
    private final String FORWARD_UPDATE_HEAD_IMAGE = "org/updateHeadImage";
    private final String SUCCESS = "success";

    private Map<String,String> result = null;

    /**
     * 跳转到添加管理员页面
     * @return
     */
    @RequestMapping("/forwardAddManager")
    public ModelAndView forward_addManager() {
        logger.debug("<==");
        Map<String, Object> data = null;
        try {
            // 找出系统角色，保存到list，存到model
            List<Role> roles = roleService.findRoles();
            data = new HashMap<String, Object>();
            data.put("roles", roles);
        } catch (Exception e) {
            logger.debug("error ==>" + e.getMessage());
        }

        logger.debug("==>");
        return new ModelAndView(FORWARD_ADD_MANAGER, data);
    }

    /**
     * 添加管理员
     * @param manager - 管理员信息
     * @param roleId - 角色id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addManager", method = RequestMethod.POST)
    public Object addManager(Manager manager, Integer roleId) {
        logger.debug("<== [manager:" + manager + ", roleId:" + roleId + "]");

        try {
            // 保存管理员
            // 设置管理员状态
            manager.setState("未冻结");
            // 添加管理员默认头像
            manager.setHeadImage("defaultHeadImage");
            // 密码进行md5算法加密
            manager.setPassword(MD5Util.getMD5String(manager.getPassword()));
            managerService.save(manager);
            Integer managerId = managerService.findByUsername(manager.getUsername()).getId();

            // 关联角色
            managerService.associateRoleById(managerId, roleId);

            // 返回给页面的json数据
            result = DWZJsonUtil.closeCurrentAndRefresh("showManagers");
        } catch (Exception e) {
            result = DWZJsonUtil.error("添加失败");
            logger.debug("error ==>" + e.getMessage());
        }

        logger.debug("==> [result:" + result + "]");
        return result;
    }

    /**
     * 列出所有管理员
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/listManager", method = RequestMethod.GET)
    public ModelAndView listManager(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum +", pageSize:" + pageSize + "]");
        Map<String, Object> data = new HashMap<String, Object>();
        try {
            // 找出系统所有管理员分页数据，存到model
            Page page = managerService.getPageData(pageNum, pageSize);
            data.put("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
        return new ModelAndView(FORWARD_LIST_MANAGER, data) ;
    }

    /**
     * 查看管理员详情
     * @param managerId - 管理员id
     * @return
     */
    @RequestMapping(value = "/detailManager", method = RequestMethod.GET)
    public ModelAndView detailManager(Integer managerId) {
        logger.debug("<== [managerId:" + managerId + "]");
        try {
            Manager manager = managerService.findById(managerId);
            Role role = new Role();
            role.setManagerId(managerId);
            role = roleService.findOne(role);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("manager", manager);
            map.put("role", role);

            logger.debug("==> [manager:" + manager + ", role:" + role + "]");
            return new ModelAndView(FORWARD_DETAIL_MANAGER, map);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
        }

        logger.debug("==>");
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/updateManager", method = RequestMethod.POST)
    public Object updateManager(Manager manager) {
        logger.debug("<== [manager:" + manager + "]");
        try {
            managerService.updateManager(manager);
            result = DWZJsonUtil.closeCurrentAndRefresh("showManagers");

        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("更新失败");
        }
        logger.debug("==>");
        return result;
    }

    @RequestMapping("/forwardUpdateHeadImage")
    public ModelAndView forwardUpdateHeadImage(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = null;
        try {
            modelAndView = new ModelAndView();
            modelAndView.setViewName(FORWARD_UPDATE_HEAD_IMAGE);
            modelAndView.addObject("id", id);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 头像上传
     * @param id - 管理员id
     * @param file - 文件
     * @param request -
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateHeadImage", method = RequestMethod.POST)
    public Object updateHeadImage(@RequestParam("id")Integer id, @RequestParam("headImage") MultipartFile file, HttpServletRequest request) {
        logger.debug("<== [id:" + id + ", file:" + file + "]");
        FileOutputStream out = null;
        File filePath = null;

        try {
            if (!file.isEmpty()) {
                // 获取文件字节数据
                byte[] bytes = file.getBytes();
                // 获取文件保存路径
                filePath = new File(request.getSession().getServletContext().getRealPath("/upload/managerHeadImage/"));
                // 若是目录不存在则创建，使用mkdirs
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                System.out.println("filePaht:" + filePath);
                // 图片名使用管理员id
                // 以字节流写出
                out = new FileOutputStream(filePath + "/" + id + ".png");
                out.write(bytes);

                // 获取管理员对象，并更新headImage字段
                Manager manager = managerService.findById(id);
                manager.setHeadImage(""+id);
                managerService.update(manager);

                // 返回页面shuju
                result = DWZJsonUtil.successAndCloseCurrent();
//                result = DWZJsonUtil.closeCurrentAndRefresh("detailManager");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("上传失败");
        }

        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/freezeManager")
    public Object freezeManager(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            // 找出manager，并设置冻结
            Manager manager = managerService.findById(id);
            manager.setState("冻结");
            // 更新
            managerService.update(manager);

            result = DWZJsonUtil.successAndRefresh("showManagers");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙，请稍后...");
        }

        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/recoverManager")
    public Object recoverManager(Integer id) {
        logger.debug("<== [id:" + id + "]");
        try {
            // 找出manager，并设置未冻结
            Manager manager = managerService.findById(id);
            manager.setState("未冻结");
            // 更新
            managerService.update(manager);

            result = DWZJsonUtil.successAndRefresh("showManagers");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙，请稍后...");
        }

        logger.debug("==>");
        return result;
    }
}
