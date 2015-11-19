package love.drose.gms.service.impl;

import com.github.pagehelper.PageHelper;
import love.drose.gms.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Service基类
 * Created by lovedrose on 2015/11/18.
 */
public abstract class BaseService<T> implements IService<T> {

    private Class<T> clazz;

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class<T>) pt.getActualTypeArguments()[0];

    }

    @Override
    public T findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T findOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public int save(T model) {
        return mapper.insert(model);
    }

    @Override
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delete(T model) {
        return mapper.delete(model);
    }

    @Override
    public int update(T model) {
        return mapper.updateByPrimaryKey(model);
    }

    @Override
    public int updateNotNull(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> getPageData(int pageNum, int pageSize) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        return selectByExample(example);
    }
}
