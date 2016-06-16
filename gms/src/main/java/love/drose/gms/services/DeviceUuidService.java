package love.drose.gms.services;

import love.drose.gms.models.DeviceUuid;

/**
 * Created by lovedrose on 3/29/16.
 */
public interface DeviceUuidService extends IService<DeviceUuid> {
    DeviceUuid findByUserId(Integer userId);
}
