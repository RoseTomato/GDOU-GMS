import love.drose.gms.models.Manager;
import love.drose.gms.services.ManagerService;
import love.drose.gms.utils.MD5Util;
import love.drose.gms.utils.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/22.
 */
@SuppressWarnings("ALL")
public class TestManager extends BaseTest {

    @Autowired
    private ManagerService managerService;

    @Test
    public void testFindAll() {
        System.out.println(managerService.findAll());
    }

    @Test
    public void testUpdate() {
        Manager manager = managerService.findByUsername("lovedrose");
        String password = MD5Util.getMD5String(manager.getPassword());
        System.out.println(password);
        manager.setPassword(password);

        managerService.update(manager);
    }

    @Test
    public void testGetPageData() {
        Page page =  managerService.getPageData(1, 10);
        List<Manager> list = page.getList();
        for (Manager manager : list) {
            System.out.println(manager.getBirthday());
        }
    }

    @Test
    public void testFindByUsername() {
        System.out.println(managerService.findByUsername("admin"));
    }

    @Test
    public void findNamesByIds() {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(5);
        ids.add(6);
        ids.add(7);
        System.out.println(managerService.findNamesByIds(ids));
    }
}
