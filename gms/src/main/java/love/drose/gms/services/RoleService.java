package love.drose.gms.services;

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

    /**
     * 找出系统角色
     * @return
     */
    List<Role> findRoles();

    /**
     * 根据角色名找出关联的管理员id
     * @param name
     * @return
     */
    List<Integer> findManagerIdsByRoleName(String name);
}
