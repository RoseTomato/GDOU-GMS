package love.drose.gms.controllers;

import love.drose.gms.models.Competition;
import love.drose.gms.models.DeviceUuid;
import love.drose.gms.models.Message;
import love.drose.gms.utils.APNSUtil;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.DateUtil;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.POST;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovedrose on 2/21/16.
 */
@RequestMapping("competitionHandler")
@Controller
public class CompetitionHandler extends BaseHandler {

    /**
     * 申请赛事
     *
     * @param name        - 赛事名
     * @param description － 描述
     * @param startTime   － 开始时间
     * @param endTime     - 结束时间
     * @param place       - 地点
     * @param applyer     － 申请人
     * @param sponsor     － 赞助商
     * @param scale       － 规模
     * @return
     */
    @RequestMapping(value = "applyCompetition", method = RequestMethod.POST)
    @ResponseBody
    public Object applyCompetition(String name, String description, String startTime, String endTime,
                                   String place, String applyer, String sponsor, Integer scale, Integer userId) {
        logger.debug("<== [name:" + name + ", description:" + description + ", startTime:" + startTime + "," +
                "endTime:" + endTime + ", place:" + place + ", applyer:" + applyer + ", sponsor:" + sponsor + ", scale:" + scale + ", userId:" + userId + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Competition competition = new Competition();
            competition.setName(name);
            competition.setDescription(description);
            competition.setStartTime(DateUtil.convertStringToDate(DateUtil.DATETIME, startTime));
            competition.setEndTime(DateUtil.convertStringToDate(DateUtil.DATETIME, endTime));
            Date date = new Date();
            competition.setCreateTime(date);
            competition.setState("待审核");
            competition.setPlace(place);
            competition.setApplyer(applyer);
            competition.setSponsor(sponsor);
            competition.setScale(scale);
            competition.setStar(0.0);
            competition.setUserId(userId);

            competitionService.save(competition);

            String format = "yyyy-MM-dd HH:mm:ss";
            String strCreateTime = DateUtil.convertDateToString(format, date);
            Competition c = competitionService.findByUserIdAndTime(userId, DateUtil.convertStringToDate(format, strCreateTime));
            map.put(DATA, c);
            map.put(RESULT, OK);
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }


    /**
     * 获取用户申请的赛事
     *
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("fetchUserCompetition")
    public Object fetchUserCompoetition(Integer userId) {
        logger.debug("<== [userId:" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Competition> competitionList = competitionService.findAllByProperty("userId", userId);
            map.put(DATA, competitionList);
            map.put(RESULT, OK);
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

    /**
     * 获取全部赛事
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("fetchCompetitions")
    public Object fetchCompetitions() {
        logger.debug("<==");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Competition> competitionList = competitionService.findAll();
            map.put(DATA, competitionList);
            map.put(RESULT, OK);
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }


    @RequestMapping("/listUncheckCompetitions")
    public ModelAndView listUncheckCompetitiions(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = competitionService.findCompetitionsByState(pageNum, pageSize, "待审核");
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_UNCHECK_COMPETITIONS);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/listCheckedCompetitions")
    public ModelAndView listCheckedCompetitiions(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = competitionService.findCompetitionsByState(pageNum, pageSize, "通过");
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_CHECKED_COMPETITIONS);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("/listCompetitions")
    public ModelAndView listCompetitiions(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Page page = competitionService.findCompetitionsByState(pageNum, pageSize, null);
            modelAndView.addObject("page", page);
            modelAndView.setViewName(TO_LIST_COMPETITIONS);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    @RequestMapping("toCheckCompetition")
    public ModelAndView toCheckCompetition(Integer competitionId) {
        logger.debug("<== [competitionId:" + competitionId);
        ModelAndView modelAndView = new ModelAndView();
        try {
            // 获取场地分页数据
            Competition competition = competitionService.findById(competitionId);
            modelAndView.addObject("competition", competition);
            modelAndView.setViewName(TO_DETAIL_COMPETITION);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            modelAndView.setViewName(NO_RESOURCE);
            e.printStackTrace();
        }
        logger.debug("==>");
        return modelAndView;
    }

    /**
     * 赛事审核
     *
     * @param competition
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkCompetition", method = RequestMethod.POST)
    public Object checkCompetition(Competition competition) {
        logger.debug("<==[competition:" + competition + "]");
        try {
            // 修改state
            competition.setState("通过");
            competitionService.update(competition);

            // 获取设备uuid
            DeviceUuid deviceUuid = deviceUuidService.findByUserId(competition.getUserId());
            // 推送一条通知
            String deviceToken = deviceUuid.getUuid();
            APNSUtil.pushCombinedNotification(deviceToken, "恭喜您的赛事审核通过了", 1, APNSUtil.DEFAULT_SOUND);
            result = DWZJsonUtil.closeCurrentAndRefresh("showUncheckCompetitions");

            // 添加一条消息
            Message message = new Message();
            message.setState("未读");
            message.setUserId(competition.getUserId());
            message.setType("赛事");
            message.setRank("重要");
            message.setTitle("赛事审核");
            message.setContent("恭喜您的赛事审核通过了");
            messageService.save(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/fetchCheckedCompetitions")
    public Object fetchCheckedCompetitions(Integer pageNum, Integer pageSize) {
        logger.debug("<== [pageNum:" + pageNum + ", pageSize:" + pageSize + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取场地分页数据
            Page page = competitionService.findCompetitionsByState(pageNum, pageSize, "通过");
            map.put(DATA, page.getList());
            map.put("hasNext", page.hasNextPage());
            map.put(RESULT, OK);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return map;
    }

}
