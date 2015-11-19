package love.drose.gms.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 * Created by lovedrose on 2015/11/18.
 */
@Service
public interface IService<T> {

    /**
     * 根据id查询模型信息
     * @param id - 编号
     * @return - 模型信息
     */
    T findById(Object id);

    /**
     * 根据实体不为null的条件查询
     * @param t - 模型
     * @return - 模型信息
     */
    T findOne(T t);

    /**
     * 获取所有模型数据
     * @return
     */
    List<T> findAll();

    /**
     * 保存模型信息到数据库
     * @param model - 模型
     * @return - 保存结果
     */
    int save(T model);

    /**
     * 根据id删除模型信息
     * @param id - 编号
     * @return - 删除结果
     */
    int deleteById(Object id);

    /**
     * 根据模型不为null的条件删除模型
     * @param model - 模型
     * @return - 删除结果
     */
    int delete(T model);

    /**
     * 更新模型信息，其中条件为主键
     * @param model - 模型
     * @return - 更新结果
     */
    int update(T model);

    /**
     * 根据模型不为null的条件更新模型信息
     * @param model - 模型
     * @return - 更新结果
     */
    int updateNotNull(T model);

    /**
     * 根据模型条件查询
     * @param example - 带条件的模型
     * @return - 模型List集合
     */
    List<T> selectByExample(Object example);

    /**
     * 获取模型的分页数据
     * @param pageNum - 当前页
     * @param pageSize - 页面大小
     * @return
     */
    List<T> getPageData(int pageNum, int pageSize);

    //TODO others...
}
