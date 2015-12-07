package love.drose.gms.controllers;

import love.drose.gms.models.User;
import love.drose.gms.services.UserService;
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
import java.util.Map;

/**
 * 用户控制器.
 * Created by lovedrose on 2015/11/25.
 */
@Controller
@RequestMapping("/userHandler")
public class UserHandler extends BaseHandler {

    Logger logger = LogManager.getLogger(UserHandler.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public Object addUser(User user) {
        logger.debug("<== [user:" + user + "]");
        try {
            // 设置用户状态
            user.setState("未冻结");
            // 设置用户默认头像
            user.setHeadImage("defaultHeadImage");
            // 对密码进行md5算法加密
            user.setPassword(MD5Util.getMD5String(user.getPassword()));

            // 保存
            userService.save(user);
            result = DWZJsonUtil.closeCurrentAndRefresh("showUsers");
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("系统正忙...");
            e.printStackTrace();
        }

        logger.debug("==>");
        return result;
    }

    @RequestMapping("/listUser")
    public ModelAndView listUser(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取User分页数据
            Page page = userService.getPageData(pageNum, pageSize);
            // 添加到modelAndView
            modelAndView.addObject("page", page);
            // 设置跳转页面
            modelAndView.setViewName(TO_LIST_USER);
        } catch (Exception e) {
            modelAndView.setViewName(NO_RESOURCE);
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/detailUser")
    public ModelAndView detailUser(Integer id) {
        logger.debug("<== [id:" + id + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取User模型
            User user = userService.findById(id);
            // 添加到modelAndView
            modelAndView.addObject("user", user);
            // 设置跳转页面
            modelAndView.setViewName(TO_DETAIL_USER);
        } catch (Exception e) {
            modelAndView.setViewName(NO_RESOURCE);
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/forwardUpdateHeadImage")
    public ModelAndView forwardUpdateHeadImage(Integer id) {
        logger.debug("<== [id:" + id +"]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 将id传到页面
            if (id != null) {
                modelAndView.addObject("id", id);
                // 设置跳转页面
                modelAndView.setViewName(TO_UPDATE_USER_HEAD_IMAGE);
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/updateHeadImage", method = RequestMethod.POST)
    public Object updateHeadImage(Integer id, @RequestParam("headImage")MultipartFile file, HttpServletRequest request) {
        logger.debug("<== [id:" + id + ", file:" + file + "]");
        FileOutputStream out = null;
        File filePath = null;

        try {
            if (!file.isEmpty()) {
                // 获取文件字节数据
                byte[] bytes = file.getBytes();
                // 获取文件保存路径
                filePath = new File(request.getSession().getServletContext().getRealPath("/upload/userHeadImage/"));
                // 若是目录不存在则创建，使用mkdirs
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                System.out.println("filePath:" + filePath);
                // 图片名使用管理员id
                // 以字节流写出
                out = new FileOutputStream(filePath + "/" + id + ".png");
                out.write(bytes);

                // 获取管理员对象，并更新headImage字段
                User user = userService.findById(id);
                user.setHeadImage(""+id);
                userService.update(user);

                // 返回页面数据
                result = DWZJsonUtil.successAndCloseCurrent();
//                result = DWZJsonUtil.closeCurrentAndRefresh("detailUser");
            }
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            result = DWZJsonUtil.error("上传失败");
        }

        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public Object updateUser(User user) {
        logger.debug("<== [user:" + user + "]");
        try {
            if (user != null) {
                userService.updateUser(user);
                result = DWZJsonUtil.closeCurrentAndRefresh("showUsers");
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
    @RequestMapping("/freezeOrRecoverUser")
    public Object freezeOrRecoverUser(Integer id, String operation) {
        logger.debug("<== [id:" + id + ", operation:" + operation +"]");
        try {
            if (id != null && null != operation) {
                // 获取用户
                User user = userService.findById(id);
                // 冻结操作
                if ("freeze".equals(operation)) {
                    user.setState("冻结");
                } else if ("recover".equals(operation)) {
                    user.setState("未冻结");
                }
                // 更新
                userService.update(user);
                result = DWZJsonUtil.successAndRefresh("showUsers");
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
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String username, String password) {
        logger.debug("<== [username:" + username + ", password:" + password + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据用户名和密码找到用户
            User user = userService.findByUsernameAndPassword(username, password);
            if (user != null) {
                map.put("result", "ok");
                map.put("data", user);
            } else {
                map.put("result", "failure");
            }
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }
}
