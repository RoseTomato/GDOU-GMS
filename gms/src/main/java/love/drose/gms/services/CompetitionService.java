package love.drose.gms.services;

import love.drose.gms.models.Competition;
import love.drose.gms.utils.Page;

import java.util.Date;

/**
 * Created by lovedrose on 2/21/16.
 */
public interface CompetitionService extends IService<Competition> {

    /**
     * 通过用户id和创建时间查询出赛事
     * @param userId
     * @param createTime
     * @return
     */
    Competition findByUserIdAndTime(Integer userId, Date createTime);

    /**
     * 获取待审核的赛事分页数据
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page findUncheckCompetitions(Integer pageNum, Integer pageSize);

    /**
     * 根据审核条件获取赛事分页数据
     * @param pageNum
     * @param pageSize
     * @param state
     * @return
     */
    Page findCompetitionsByState(Integer pageNum, Integer pageSize, String state);
}
