import love.drose.gms.models.Role;
import love.drose.gms.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * RolseService测试单元.
 * Created by lovedrose on 2015/11/19.
 */
public class TestRoleService extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testSave() {
        Role role = null;
        for (int i = 10; i < 20; i++) {
            role = new Role();
            role.setName(i+"号管理员");
            roleService.save(role);
        }
    }

    @Test
    public void testDeleteById() {
        roleService.deleteById(7);
    }

    @Test
    public void testUpdate() {
        Role role = new Role();
        role.setId(6);
        role.setName("角色管理员");
        roleService.update(role);
    }

    @Test
    public void testFindById() {
        System.out.println(roleService.findById(6));
    }

    @Test
    public void testFindAll() {
        List<Role> roles = roleService.findAll();
        System.out.println(roles.size());
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            System.out.println(role);
        }
    }

    @Test
    public void testFindPageData() {
        List<Role> roles = roleService.selectByRole(new Role(), 2, 9);
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            System.out.println(role);
        }
    }

    @Test
    public void testGetPageData() {
        List<Role> roles = roleService.getPageData(1, 10);
        for (int i = 0; i < roles.size(); i++) {
            Role role = roles.get(i);
            System.out.println(role);
        }
    }
}
