import love.drose.gms.services.SportStarService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lovedrose on 4/7/16.
 */
public class TestSportStarService extends BaseTest {

    @Autowired
    private SportStarService sportStarService;

    @Test
    public void testFindAllOrderByProperty() {
        System.out.println(sportStarService.findAllOrderByProperty("score"));
    }
}
