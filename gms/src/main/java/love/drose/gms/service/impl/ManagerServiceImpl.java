package love.drose.gms.service.impl;

import love.drose.gms.models.Manager;
import love.drose.gms.models.Role;
import love.drose.gms.service.ManagerService;
import love.drose.gms.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理员Service
 * Created by lovdrose on 2015/11/20.
 */
@Service("managerService")
public class ManagerServiceImpl extends BaseService<Manager> implements ManagerService {

    private static Logger logger =  LogManager.getLogger(ManagerServiceImpl.class.getName());

    @Autowired
    private RoleService roleService;

    @Override
    public Manager findByUsername(String username) {
        logger.debug("in <==");
        Manager manager = new Manager();
        manager.setUsername(username);
        manager = findOne(manager);

        if (manager.getId() != null) {
            logger.debug("out ==>");
            return manager;
        }

        logger.debug("out ==>");
        return null;
    }

    @Override
    public void associateRoleById(Integer managerId, Integer roleId) {
        logger.debug("in <==");

        String roleName = roleService.findById(roleId).getName();
        Role role = new Role();
        role.setName(roleName);
        role.setManagerId(managerId);
        roleService.save(role);

        logger.debug("out ==>");
    }

    @Override
    public Set<String> findRolesOfManagerById(Integer id) {
        logger.debug("in <==");

        Set<String> set = null;
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("managerId", id);
        List<Role> roles = roleService.selectByExample(example);

        if (roles.size() > 0) {
            set = new HashSet<String>();
            for (Role role : roles) {
                set.add(role.getName());
            }
            logger.debug("out ==>");
            return set;
        }

        logger.debug("out ==>");
        return null;
    }
}
