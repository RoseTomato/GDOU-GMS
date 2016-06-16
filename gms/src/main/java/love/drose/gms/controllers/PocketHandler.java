package love.drose.gms.controllers;

import love.drose.gms.models.Pocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lovedrose on 4/14/16.
 */
@Controller
@RequestMapping("/pocketHandler")
public class PocketHandler extends BaseHandler {

    @ResponseBody
    @RequestMapping("/fetchUserMoney")
    public Object fetchUserMoney(Integer userId) {
        logger.debug("<== [userId:"+userId+"]");
        Map<String, Object> map = new HashMap<String, Object>();
        Pocket pocket = pocketService.findByUserId(userId);
        map.put(DATA, pocket);
        map.put(RESULT, OK);

        logger.debug("==>");
        return map;
    }
}
