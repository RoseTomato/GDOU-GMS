package love.drose.gms.services.impl;

import love.drose.gms.models.User;
import love.drose.gms.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 用户service
 * Created by lovedrose on 2015/11/26.
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {

    Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public void updateUser(User user) {
        logger.debug("<== [user:" + user + "]");
        try {
            // 若传过来的user头像属性为null,则取出旧头像设置到新user
            if (user.getHeadImage() != null) {
                String oldHeadImage = findById(user.getId()).getHeadImage();
                user.setHeadImage(oldHeadImage);
            }

            // 更新
            update(user);
        } catch (Exception e) {
            logger.error("error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
    }
}
