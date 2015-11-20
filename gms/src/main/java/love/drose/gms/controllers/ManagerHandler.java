package love.drose.gms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 管理员控制类
 * Created by lovedrose on 2015/11/18.
 */
@Controller
@RequestMapping("/managerHandler")
public class ManagerHandler {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String name, @RequestParam String password) {
        System.out.println("name:" + name + ", password:" + password);
        return "backstageIndex";
    }
}
