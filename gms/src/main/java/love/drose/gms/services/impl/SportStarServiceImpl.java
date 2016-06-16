package love.drose.gms.services.impl;

import love.drose.gms.mappers.SportStarMapper;
import love.drose.gms.models.SportStar;
import love.drose.gms.models.User;
import love.drose.gms.services.SportStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lovedrose on 4/7/16.
 */
@Service("sportStarService")
public class SportStarServiceImpl extends BaseService<SportStar> implements SportStarService {

    @Autowired
    private SportStarMapper sportStarMapper;

    @Override
    public void increaseScore(User user) {
        logger.debug("<== [user:"+user+"]");
        Example example =  new Example(SportStar.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("userId", user.getId());
        List<SportStar> sportStarList = selectByExample(example);

        if (sportStarList != null && sportStarList.size() > 0) {
            SportStar ss = sportStarList.get(0);
            ss.setScore(ss.getScore() + 1);
            update(ss);
        } else {
            SportStar ss = new SportStar();
            ss.setHead(user.getHeadImage());
            ss.setUserId(user.getId());
            ss.setScore(1);
            ss.setName(user.getName());
            save(ss);
        }

        logger.debug("==>");
    }

    @Override
    public List<SportStar> findAllOrderByProperty(String property) {
        return this.sportStarMapper.findAllOrderByProperty(property);
    }
}
