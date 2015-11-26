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

}
