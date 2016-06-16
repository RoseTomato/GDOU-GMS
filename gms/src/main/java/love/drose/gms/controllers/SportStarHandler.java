package love.drose.gms.controllers;

import love.drose.gms.models.SportStar;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovedrose on 4/7/16.
 */
@Controller
@RequestMapping("/sportStarHandler")
public class SportStarHandler extends BaseHandler {

    @ResponseBody
    @RequestMapping("fetchSportStars")
    public Object fetchSportStars() {
        logger.debug("<==");
        Map<String, Object> map = new HashMap<String, Object>();
        List<SportStar> sportStars = sportStarService.findAllOrderByProperty("score");
        map.put(DATA, sportStars);
        map.put(RESULT, OK);
        logger.debug("==>");
        return map;
    }
}
