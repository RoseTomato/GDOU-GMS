import love.drose.gms.mappers.RoleMapper;
import love.drose.gms.models.Role;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用接口测试.
 * Created by lovedrose on 2015/11/18.
 */
public class TestGeneralMapper extends BaseTest {

    @Autowired
    private SqlSession sqlSession;

    private RoleMapper mapper = null;

    @Before
    public void init() {
        mapper = sqlSession.getMapper(RoleMapper.class);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Role role = mapper.selectByPrimaryKey(5);
        System.out.println(role);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Role role = new Role();
        role.setId(7);
        role.setName("Test");
        mapper.updateByPrimaryKey(role);
    }

}
