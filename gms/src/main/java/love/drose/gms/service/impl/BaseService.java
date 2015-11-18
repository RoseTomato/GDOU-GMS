package love.drose.gms.service.impl;

import love.drose.gms.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Service基类
 * Created by lovedrose on 2015/11/18.
 */
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
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
    public int deleteByKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int delete(T model) {
        return mapper.delete(model);
    }

    @Override
    public int updateAll(T model) {
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
}
