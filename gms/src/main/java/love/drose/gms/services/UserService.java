package love.drose.gms.services;

import love.drose.gms.models.User;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface UserService extends IService<User> {

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据用户名和密码查找用户信息.
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password)  throws Exception;

}
