package love.drose.gms.controllers;

import love.drose.gms.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色控制类.
 * Created by lovedrose on 2015/11/18.
 */
@Controller
@RequestMapping("/roleHandler")
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/testFindById")
    public String testFindById(Integer id) {
        System.out.println(roleService.findById(id));
        return "success";
    }
}
