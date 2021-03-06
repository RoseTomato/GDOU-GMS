package love.drose.gms.services.impl;

import love.drose.gms.models.Privilege;
import love.drose.gms.services.PrivilegeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 权限Service实现类.
 * Created by lovedrose on 2015/11/21.
 */
@Service("privilegeService")
public class PrivilegeServiceImpl extends BaseService<Privilege> implements PrivilegeService {

    private static Logger logger =  LogManager.getLogger(PrivilegeServiceImpl.class.getName());

    @Override
    public Collection<String> findPrivilegesOfRoleById(Integer id) {
        logger.debug("in <==");
        Set<String> set = null;

        Example example = new Example(Privilege.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("roleId", id);
        List<Privilege> privileges = selectByExample(example);

        if (privileges.size() > 0) {
            set = new HashSet<String>();
            for (Privilege privilege : privileges) {
                set.add(privilege.getName());
            }
            logger.debug("out ==>");
            return set;
        }

        logger.debug("out ==>");
        return null;
    }

    @Override
    public void deleteByRoleId(Integer id) {
        logger.debug("in <==");
        try {
            Example example = new Example(Privilege.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("roleId", id);
            mapper.deleteByExample(example);
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            e.printStackTrace();
        }

        logger.debug("out ==>");
    }

    @Override
    public List<Privilege> findPrivileges() {
        logger.debug("in <==");
        List<Privilege> privileges = null;
        try {
            privileges = findAllWhereIsNull("roleId");
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            e.printStackTrace();
        }

        logger.debug("==>");
        return privileges;
    }
}
