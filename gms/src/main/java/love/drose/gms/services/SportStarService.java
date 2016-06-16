package love.drose.gms.services;

import love.drose.gms.models.SportStar;
import love.drose.gms.models.User;

import java.util.List;

/**
 * Created by lovedrose on 4/7/16.
 */
public interface SportStarService extends IService<SportStar> {
    public void increaseScore(User user);

    public List<SportStar>  findAllOrderByProperty(String property);
}