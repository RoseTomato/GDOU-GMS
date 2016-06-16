package love.drose.gms.services.impl;

import love.drose.gms.models.Competition;
import love.drose.gms.models.DeviceUuid;
import love.drose.gms.services.DeviceUuidService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lovedrose on 3/29/16.
 */
@Service("deviceUuidService")
public class DeviceUuidServiceImpl extends BaseService<DeviceUuid> implements DeviceUuidService {

    @Override
    public DeviceUuid findByUserId(Integer userId) {
        logger.debug("<==");
        DeviceUuid deviceUuid = null;
        try {
            Example example = new Example(DeviceUuid.class);
            Example.Criteria  criteria = example.createCriteria();

            criteria.andEqualTo("userId", userId);

            List<DeviceUuid> deviceUuids = selectByExample(example);
            if (deviceUuids != null) {
                deviceUuid = deviceUuids.get(0);
            }
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return deviceUuid;
    }
}
