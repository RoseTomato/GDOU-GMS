package love.drose.gms.service;

import love.drose.gms.models.Role;

import java.util.List;

/**
 * 角色Service接口
 * Created by lovedrose on 2015/11/18.
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据条件分页查询
     * @param role
     * @param page
     * @param rows
     * @return
     */
    List<Role> selectByRole(Role role, int page, int rows);

}
