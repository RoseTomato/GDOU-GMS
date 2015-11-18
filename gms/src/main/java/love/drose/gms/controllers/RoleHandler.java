package love.drose.gms.controllers;

import love.drose.gms.models.Role;
import love.drose.gms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2015/11/18.
 */
@Controller
@RequestMapping("/role")
public class RoleHandler {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Role role) {
        roleService.save(role);
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(Integer id) {
        Role role = new Role(id, null);
        System.out.println("delete ================>>>" + roleService.delete(role));
        return "success";
    }

    @RequestMapping("/update")
    public String update() {
        Role role = new Role();
        role.setId(7);
        role.setName("Irving");
        System.out.println("update ===================== >>>" + roleService.updateAll(role));
        return "success";
    }

    @RequestMapping("/findById")
    public String findById(Integer id) {
        Role role = new Role();
        role.setId(id);
        System.out.println("findById ===================== >>>" + roleService.findOne(role));
        return "success";
    }

    @RequestMapping("/findAll")
    public String findAll() {
        System.out.println("findById ===================== >>>" + roleService.findAll());
        return "success";
    }
}
