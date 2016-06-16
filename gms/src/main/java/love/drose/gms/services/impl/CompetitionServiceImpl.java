package love.drose.gms.services.impl;

import com.github.pagehelper.PageHelper;
import love.drose.gms.models.Competition;
import love.drose.gms.services.CompetitionService;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by lovedrose on 2/21/16.
 */
@Service("competitionService")
public class CompetitionServiceImpl extends BaseService<Competition> implements CompetitionService {

    @Override
    public Competition findByUserIdAndTime(Integer userId, Date createTime) {
        logger.debug("<== [userId:"+userId+", createTime:"+createTime+"]");
        Competition competition = null;
        try {
            Example example = new Example(Competition.class);
            Example.Criteria  criteria = example.createCriteria();

            criteria.andEqualTo("createTime", createTime);
            criteria.andEqualTo("userId", userId);

            List<Competition> competitions = selectByExample(example);
            if (competitions != null) {
                competition = competitions.get(0);
            }
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return competition;
    }


    @Override
    public Page findUncheckCompetitions(Integer pageNum, Integer pageSize) {
        logger.debug("<==[pageNum:" +pageNum + ", pageSize:" + pageSize);
        try {
            // 查询出模型的总记录数
            Example example = new Example(Competition.class);
            Example.Criteria criteria = example.createCriteria();

            // 待审核的条件
            criteria.andEqualTo("state", "待审核");

            // 查询总记录数
            Integer totalRecord = mapper.selectCountByExample(example);

            // 封装到Page对象里面
            Page page = new Page(pageSize, pageNum, totalRecord);

            // 获取模型的分页list集合
            // 分页查询
            PageHelper.startPage(pageNum, pageSize);
            List<Competition> list = selectByExample(example);

            if (list.size() > 0) {
                page.setList(list);

                logger.debug("==> [page:" + page + "]");
                return page;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==> null");
        return null;
    }

    @Override
    public Page findCompetitionsByState(Integer pageNum, Integer pageSize, String state) {
        logger.debug("<==[pageNum:" +pageNum + ", pageSize:" + pageSize);
        try {
            // 查询出模型的总记录数
            Example example = new Example(Competition.class);
            Example.Criteria criteria = example.createCriteria();

            if (!"".equals(state) && state != null) {
                // 待审核的条件
                criteria.andEqualTo("state", state);
            }


            // 查询总记录数
            Integer totalRecord = mapper.selectCountByExample(example);

            // 封装到Page对象里面
            Page page = new Page(pageSize, pageNum, totalRecord);

            // 获取模型的分页list集合
            // 分页查询
            PageHelper.startPage(pageNum, pageSize);
            List<Competition> list = selectByExample(example);

            if (list.size() > 0) {
                page.setList(list);

                logger.debug("==> [page:" + page + "]");
                return page;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==> null");
        return null;
    }
}
