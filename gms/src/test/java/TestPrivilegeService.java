import love.drose.gms.models.Privilege;
import love.drose.gms.services.PrivilegeService;
import love.drose.gms.utils.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/11/21.
 */
public class TestPrivilegeService extends BaseTest {

    @Autowired
    private PrivilegeService privilegeService;

    @Test
    public void testSave() {
        // 给组织结构管理员添加几个权限
//        Privilege p1 = new Privilege();
//        p1.setName("新增管理员");
//        p1.setRoleId(2);
//        privilegeService.save(p1);
//
//        Privilege p2 = new Privilege();
//        p2.setName("删除管理员");
//        p2.setRoleId(2);
//        privilegeService.save(p2);
//
//        Privilege p3 = new Privilege();
//        p3.setName("更新管理员");
//        p3.setRoleId(2);
//        privilegeService.save(p3);
//
//        Privilege p4 = new Privilege();
//        p4.setName("查询管理员");
//        p4.setRoleId(2);
//        privilegeService.save(p4);

        // 给超级管理员设置权限
        Privilege p5 = new Privilege();
        p5.setName("新增管理员");
        p5.setRoleId(1);
        privilegeService.save(p5);
    }

    @Test
    public void testFindPrivilegesByRoleId() {
        System.out.println(privilegeService.findPrivilegesOfRoleById(5));
    }

    @Test
    public void testFindByName() {
        System.out.println(privilegeService.findByName("新增管理员"));
    }

    @Test
    public void testDeleteByRoleId() {
        privilegeService.deleteByRoleId(2);
    }

    @Test
    public void findPrivileges() {
        System.out.println(privilegeService.findPrivileges());
    }

    @Test
    public void findPageDataWhereIsNull() {
        Page page = privilegeService.findPageDataWhereIsNull(1, 20, "roleId");
        List<Privilege> privileges = page.getList();
        for (Privilege p :privileges) {
            System.out.println(p.getId());
        }
    }

    @Test
    public void testFindAllByName() {
        System.out.println(privilegeService.findAllByName("测试"));
    }
}
