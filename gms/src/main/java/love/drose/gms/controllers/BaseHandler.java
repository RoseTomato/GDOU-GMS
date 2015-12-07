package love.drose.gms.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 控制器基类.
 * Created by lovedrose on 2015/11/26.
 */
public class BaseHandler {

    Logger logger = LogManager.getLogger(BaseHandler.class);

    /**
     * 用户返回客户端的结果
     */
    Map<String, String> result = null;

    /**
     * 跳转到某个模块页面的公共方法
     * @param module - 模块名
     * @param page - 页面名
     * @return
     */
    @RequestMapping(value = "/forward_{module}_{page}", method = RequestMethod.GET)
    public String forward(@PathVariable("module") String module, @PathVariable("page") String page) {
        logger.debug("in <== [module:" + module + ", page:" + page + "]");

        String result = "";

        if (module != null && !module.isEmpty()) {
            result += module;
        }

        if (page != null && !page.isEmpty()) {
            result += "/";
            result += page;
        }

        logger.debug("out ==> [result:" + result + "]");
        return result;
    }

    /** ============================ 页面跳转常量 ====================== */
    public final String BACKSTAGE_ENTRANCE = "backstageEntrance";
    public final String BACKSTAGE_INDEX = "backstageIndex";
    public final String NO_PRIVILEGE = "403";
    public final String NO_RESOURCE = "404";
    public final String REDIRECT_TO_LOGIN = "redirect:/backstageEntrance";
    public final String REDIRECT_TO_INDEX = "redirect:/toIndex";
    public final String SUCCESS = "success";

    public final String FORWARD_ADD_MANAGER = "org/addManager";
    public final String FORWARD_LIST_MANAGER = "org/listManager";
    public final String FORWARD_DETAIL_MANAGER = "org/detailManager";
    public final String FORWARD_UPDATE_HEAD_IMAGE = "org/updateHeadImage";
    public final String FORWARD_ASSOCIATE_ROLE = "org/associateRole";

    public final String LIST_PRIVILEGE = "org/listPrivilege";
    public final String FORWARD_UPDATE_PRIVILEGE = "org/updatePrivilege";

    public final String LIST_ROLE = "org/listRole";
    public final String DETAIL_ROLE = "org/detailRole";
    public final String FORWARD_ASSOCIATE_PRIVILEGE = "org/associatePrivilege";

    public final String TO_LIST_USER = "user/listUser";
    public final String TO_DETAIL_USER = "user/detailUser";
    public final String TO_UPDATE_USER_HEAD_IMAGE = "user/updateHeadImage";

    public final String TO_LIST_FIELD = "field/listField";
    public final String TO_DETAILD_FIELD = "field/detailField";

}
