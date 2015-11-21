import love.drose.gms.models.Role;
import love.drose.gms.service.RoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/21.
 */
public class TestRoleService extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testSave() {
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        Role role4 = new Role();
        Role role5 = new Role();
        Role role6 = new Role();

        role1.setName("超级管理员");
        role2.setName("组织结构管理员");
        role3.setName("用户管理员");
        role4.setName("场地管理员");
        role5.setName("器材管理员");
        role6.setName("赛事管理员");

        roleService.save(role1);
        roleService.save(role2);
        roleService.save(role3);
        roleService.save(role4);
        roleService.save(role5);
        roleService.save(role6);
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

    @Test
    public void testSelectByExample() {
        Role role = new Role();
        role.setName("超级管理员");
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(role.getName())) {
            criteria.andLike("name", "%" + role.getName() + "%");
        }

        List<Role> roles = roleService.selectByExample(example);
        System.out.println(roles);
    }

}
