package love.drose.gms.controllers;

import love.drose.gms.models.Manager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 处理后台基础事件.
 * Created by lovedrose on 2015/11/20.
 */
@Controller
public class BackstageHandler_ extends BaseHandler{

    private static Logger logger =  LogManager.getLogger(BackstageHandler_.class.getName());

    /**
     * 转到后台登陆页面
     * @return
     */
    @RequestMapping(value = "/backstageEntrance", method = RequestMethod.GET)
    public String backstageEntrance() {
        logger.debug("in <==");
        logger.debug("out ==>");
        return BACKSTAGE_ENTRANCE;
    }

    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public String toIndex() {
        logger.debug("in <==");
        logger.debug("out ==>");
        return BACKSTAGE_INDEX;
    }

    /**
     * 登陆
     * @param manager
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/loginBackstage", method = RequestMethod.POST)
    public String loginBackstage(Manager manager, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        logger.debug("in <==[username:" + manager.getUsername() + ", password:" + manager.getPassword() + "]");
        String message = null;
        try {
            if (bindingResult.hasErrors()) {
                logger.error("out ==>");
                return REDIRECT_TO_LOGIN;
            }

            // 使用权限工具进行登陆，登陆成功后跳到shiro配置的successUrl
            SecurityUtils.getSubject().login(new UsernamePasswordToken(manager.getUsername(), manager.getPassword()));

        }catch (UnknownAccountException e0){
            message = "UnknownAccountException";
        }catch(IncorrectCredentialsException e1) {
            message = "IncorrectCredentialsException";
        }catch (AuthenticationException e2) {
            message = "AuthenticationException";
        }

        if (message != null && !message.isEmpty()) {
            logger.error("out ==> message:" + message);
            redirectAttributes.addAttribute("message",message);
            return REDIRECT_TO_LOGIN;
        }

        logger.debug("out ==>");
        return REDIRECT_TO_INDEX;
    }

    /**
     * 退出并返回登陆页面
     * @return
     */
    @RequestMapping(value = "/logoutBackstage", method = RequestMethod.GET)
    public String logoutBackstage() {
        logger.debug("in <==");

        // 使用权限管理工具进行退出
        SecurityUtils.getSubject().logout();

        logger.debug("out ==>");
        return REDIRECT_TO_LOGIN;
    }


    /**
     * 超出权限请求
     * @return
     */
    @RequestMapping("/403")
    public String unauthorizedRole() {
        logger.debug("in <==");
        logger.debug("out ==>");
        return NO_PRIVILEGE;
    }

}
