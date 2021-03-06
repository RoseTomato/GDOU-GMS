package love.drose.gms.services.impl;

import com.github.pagehelper.PageHelper;
import love.drose.gms.models.Privilege;
import love.drose.gms.models.Role;
import love.drose.gms.services.PrivilegeService;
import love.drose.gms.services.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色Service实现类.
 * Created by lovedrose on 2015/11/18.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private static Logger logger = LogManager.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public List<Role> selectByRole(Role role, int page, int rows) {
        logger.debug("in <== [Role:"+role+", page:"+page+", rows:"+rows+"]");

        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(role.getName())) {
            criteria.andLike("name", "%" + role.getName() + "%");
        }
        if (role.getId() != null) {
            criteria.andEqualTo("id", role.getId());
        }

        // 分页查询
        PageHelper.startPage(page, rows);

        logger.debug("out ==>");
        return selectByExample(example);
    }

    @Override
    public List<Role> findRoles() {
        logger.debug("in <==");

        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();

        // 查询未绑定管理员的角色,并且过滤掉超级管理员
        criteria.andIsNull("managerId")
                .andNotEqualTo("id", 1);

        logger.debug("out ==>");
        return selectByExample(example);
    }

    @Override
    public List<Integer> findManagerIdsByRoleName(String name) {
        logger.debug("in <==");
        List<Role> roles = null;
        List<Integer> managerIds = null;
        try {
            Example example = new Example(Role.class);
            Example.Criteria criteria = example.createCriteria();

            // 指定查询条件
            criteria.andEqualTo("name", name).andIsNotNull("managerId");
            roles = selectByExample(example);

            if (roles.size() > 0) {
                managerIds = new ArrayList<Integer>();
                for(Role role : roles) {
                    managerIds.add(role.getManagerId());
                }
            }

        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
        }

        logger.debug("out ==>");
        return managerIds;
    }

    @Override
    public void associatePrivilegeById(Integer roleId, Integer privilegeId) {
        logger.debug("<== [roleId:"+roleId+", privilegeId:" + privilegeId + "]");
        try {
            String privilegeName = privilegeService.findById(privilegeId).getName();
            Privilege privilege = new Privilege();
            privilege.setName(privilegeName);
            // 设置关联的角色id
            privilege.setRoleId(roleId);
            // 保存
            privilegeService.save(privilege);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
    }
}
