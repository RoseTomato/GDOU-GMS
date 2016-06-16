package love.drose.gms.mappers;

import love.drose.gms.models.SportStar;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lovedrose on 4/7/16.
 */
public interface SportStarMapper extends Mapper<SportStar> {

    public List<SportStar> findAllOrderByProperty(String property);
}
