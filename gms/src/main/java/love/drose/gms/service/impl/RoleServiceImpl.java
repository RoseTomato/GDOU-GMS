package love.drose.gms.service.impl;

import com.github.pagehelper.PageHelper;
import love.drose.gms.models.Role;
import love.drose.gms.service.RoleService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
@Service("roleService")
public class RoleServiceImpl extends BaseService<Role> implements RoleService {
    @Override
    public List<Role> selectByRole(Role role, int page, int rows) {
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

        return selectByExample(example);
    }
}
