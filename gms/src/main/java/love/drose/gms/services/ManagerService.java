package love.drose.gms.services;

import love.drose.gms.models.Manager;

import java.util.Set;

/**
 * Created by lovedrose on 2015/11/20.
 */
public interface ManagerService extends IService<Manager> {

    /**
     * 根据账号查询管理员
     * @param username - 账号
     * @return 管理员
     */
    Manager findByUsername(String username);

    /**
     * 给管理员关联角色
     * @param managerId - 管理员id
     * @param roleId - 要关联的角色id
     */
    void associateRoleById(Integer managerId, Integer roleId);

    /**
     * 通过管理员id找到其所有角色集合
     * @param id - 管理员id
     * @return
     */
    Set<String> findRolesOfManagerById(Integer id);

    /**
     * 更新管理员信息
     * @param manager
     */
    void updateManager(Manager manager);
}
