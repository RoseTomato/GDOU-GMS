package love.drose.gms.service;

import love.drose.gms.models.Privilege;

import java.util.Collection;

/**
 * 权限Service接口
 * Created by lovedrose on 2015/11/21.
 */
public interface PrivilegeService extends IService<Privilege> {

    /**
     * 根据角色id查找权限
     * @param id - 角色id
     * @return 权限集合
     */
    Collection<String> findPrivilegesOfRoleById(Integer id);
}
