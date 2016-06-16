package love.drose.gms.services;

import love.drose.gms.models.Equipment;
import love.drose.gms.models.Field;
import love.drose.gms.models.Pocket;

/**
 * Created by lovedrose on 4/14/16.
 */
public interface PocketService extends IService<Pocket> {
    Pocket findByUserId(Integer id);

    Boolean canPurchaseField(Integer userId, Field field, Double duration);


    void cutField(Integer userId, Field field, Double duration);
    void cutEquipment(Integer userId, Equipment field, Double duration, Integer number);


    boolean canPurchaseEquipment(Integer userId, Equipment equipment, Double duration);

}
