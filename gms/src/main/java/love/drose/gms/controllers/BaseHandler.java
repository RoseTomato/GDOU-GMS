package love.drose.gms.controllers;

import love.drose.gms.models.Activity;
import love.drose.gms.models.Message;
import love.drose.gms.models.Notice;
import love.drose.gms.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    protected FirstCategoryService firstCategoryService;
    @Autowired
    protected SecondCategoryService secondCategoryService;
    @Autowired
    protected FieldService fieldService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected ManagerService managerService;
    @Autowired
    protected PrivilegeService privilegeService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected UseFieldService useFieldService;
    @Autowired
    protected MessageService messageService;
    @Autowired
    protected EquipmentService equipmentService;
    @Autowired
    protected UseEquipmentService useEquipmentService;
    @Autowired
    protected CompetitionService competitionService;
    @Autowired
    protected DeviceUuidService deviceUuidService;
    @Autowired
    protected SportStarService sportStarService;
    @Autowired
    protected ActivityService activityService;
    @Autowired
    protected PocketService pocketService;
    @Autowired
    protected RechargeService rechargeService;
    @Autowired
    protected SalaryService salaryService;
    @Autowired
    protected NoticeService noticeService;

    /**
     * 用户返回客户端的结果
     */
    Map<String, String> result = null;

    /**
     * 跳转到某个模块页面的公共方法
     *
     * @param module - 模块名
     * @param page   - 页面名
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

    /**
     * ============================ 结 果 常 量 ======================
     */
    protected final String OK = "ok";
    protected final String RESULT = "result";
    protected final String FAILURE = "failure";
    protected final String DATA = "data";
    /**
     * ============================ 页面跳转常量 ======================
     */
    protected final String BACKSTAGE_ENTRANCE = "backstageEntrance";
    protected final String BACKSTAGE_INDEX = "backstageIndex";
    protected final String NO_PRIVILEGE = "403";
    protected final String NO_RESOURCE = "404";
    protected final String REDIRECT_TO_LOGIN = "redirect:/backstageEntrance";
    protected final String REDIRECT_TO_INDEX = "redirect:/toIndex";
    protected final String SUCCESS = "success";

    protected final String FORWARD_ADD_MANAGER = "org/addManager";
    protected final String FORWARD_LIST_MANAGER = "org/listManager";
    protected final String FORWARD_DETAIL_MANAGER = "org/detailManager";
    protected final String FORWARD_UPDATE_HEAD_IMAGE = "org/updateHeadImage";
    protected final String FORWARD_ASSOCIATE_ROLE = "org/associateRole";

    protected final String LIST_PRIVILEGE = "org/listPrivilege";
    protected final String FORWARD_UPDATE_PRIVILEGE = "org/updatePrivilege";

    protected final String LIST_ROLE = "org/listRole";
    protected final String DETAIL_ROLE = "org/detailRole";
    protected final String FORWARD_ASSOCIATE_PRIVILEGE = "org/associatePrivilege";

    protected final String TO_LIST_USER = "user/listUser";
    protected final String TO_DETAIL_USER = "user/detailUser";
    protected final String TO_UPDATE_USER_HEAD_IMAGE = "user/updateHeadImage";

    protected final String TO_LIST_FIELD = "field/listField";
    protected final String TO_LIST_NOTICE = "field/listNotices";
    protected final String TO_ADD_FIELD = "field/addField";
    protected final String TO_DETAILD_FIELD = "field/detailField";


    protected final String TO_LIST_FIRST_CATEGORY = "category/listFirstCategory";
    protected final String TO_LIST_SECOND_CATEGORY = "category/listSecondCategory";
    protected final String TO_ADD_SECOND_CATEGORY = "category/addSecondCategory";

    protected final String TO_ADD_EQUIPMENT = "equipment/addEquipment";
    protected final String TO_LIST_EQUIPMENT = "equipment/listEquipments";
    protected final String TO_DETAILD_EQUIPMENT = "equipment/detailEquipment";

    protected final String TO_LIST_UNCHECK_COMPETITIONS = "competition/listUncheckCompetitions";
    protected final String TO_LIST_CHECKED_COMPETITIONS = "competition/listCheckedCompetitions";
    protected final String TO_LIST_COMPETITIONS = "competition/listCompetitions";
    protected final String TO_DETAIL_COMPETITION = "competition/detailCompetition";

    protected final String TO_LIST_RECHARGED_RECORD = "finance/listRechargedRecord";
    protected final String TO_LIST_SALARY_RECORD = "finance/listSalaryRecord";
    protected final String TO_SHOW_CURRENT_DAY_TURNOVER = "finance/showCurrentDayTurnover";
}
