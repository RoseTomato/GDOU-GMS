package love.drose.gms.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通用接口
 * Created by lovedrose on 2015/11/18.
 */
@Service
public interface IService<T> {

    T selectByKey(Object key);

    T findOne(T t);

    List<T> findAll();

    int save(T model);

    int deleteByKey(Object key);

    int delete(T model);

    int updateAll(T model);

    int updateNotNull(T model);

    List<T> selectByExample(Object example);

    //TODO others...
}
