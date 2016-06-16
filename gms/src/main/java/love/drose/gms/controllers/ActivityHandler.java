package love.drose.gms.controllers;

import love.drose.gms.models.Activity;
import love.drose.gms.utils.DWZJsonUtil;
import love.drose.gms.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovedrose on 4/8/16.
 */
@Controller
@RequestMapping("/activityHandler")
public class ActivityHandler extends BaseHandler {

    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    @ResponseBody
    public Object addActivity(String name, String content, String startTime, String endTime) {
        logger.debug("<== [name:" + name + ", content:" + content + ", startTime:" + startTime + ", endTime:" + endTime + "]");

        try {
            Activity activity = new Activity();

            activity.setName(name);
            activity.setContent(content);
            activity.setStartTime(DateUtil.convertStringToDate(DateUtil.DATE, startTime));
            activity.setEndTime(DateUtil.convertStringToDate(DateUtil.DATE, endTime));
            activity.setState("有效");

            activityService.save(activity);

            result = DWZJsonUtil.successAndCloseCurrent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("==>");
        return result;
    }

    @ResponseBody
    @RequestMapping("/fetchActivities")
    public Object fetchActivities() {
        logger.debug("<==");
        Map<String, Object> map  = new HashMap<String, Object>();
        try {
            List<Activity> activities = activityService.findAll();
            map.put(DATA, activities);
            map.put(RESULT, OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
        return map;
    }
}
