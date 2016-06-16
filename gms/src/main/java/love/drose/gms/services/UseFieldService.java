package love.drose.gms.services;

import love.drose.gms.models.Field;
import love.drose.gms.models.UseField;

import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 */
public interface UseFieldService extends IService<UseField> {
    String useField(Field field, Integer userId, Double duration);

    List<UseField> findUserUseField(Integer userId);

    Boolean appointField(Integer userId, Field fieldId, String startTime, Double duration);

    /**
     * 根据用户id查找租用的场地
     * @param userId
     * @param fieldService
     * @return
     */
    List<Field> fecthUserAppointField(Integer userId, FieldService fieldService);
}
