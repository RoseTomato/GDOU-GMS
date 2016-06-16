import love.drose.gms.models.Equipment;
import love.drose.gms.services.EquipmentService;
import love.drose.gms.utils.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by lovedrose on 1/20/16.
 */
public class TestEquipmentService extends BaseTest {

    @Autowired
    private EquipmentService equipmentService;

    @Test
    public void test() {
        Equipment equipment = equipmentService.findByNameWithTotalIsNotNull("篮球");
        System.out.println(equipment);
    }

    @Test
    public void testFindPageDataWithTotalIsNotNull() {
        Page page = equipmentService.findPageDataWithTotalIsNotNull(1, 20);
        System.out.println(page.getList().size());

    }
}
