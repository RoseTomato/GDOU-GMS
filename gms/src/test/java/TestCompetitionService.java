import love.drose.gms.models.Competition;
import love.drose.gms.services.CompetitionService;
import love.drose.gms.utils.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by lovedrose on 3/3/16.
 */
public class TestCompetitionService extends BaseTest {
    @Autowired
    private CompetitionService competitionService;

    @Test
    public void testFindByUserIdAndTime() {
        Date date = DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", "2016-04-11 17:23:25");
        Competition competition = competitionService.findByUserIdAndTime(3, date);
        System.out.println(competition);
    }
}
