package love.drose.gms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制类
 * Created by lovedrose on 2015/11/18.
 */
@Controller
@RequestMapping("/manager")
public class ManagerHandler {

    @RequestMapping("/test")
    public String test() {
        System.out.println("test!!!");
        return "success";
    }
}
