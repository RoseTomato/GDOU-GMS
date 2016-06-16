package love.drose.gms.services;

import love.drose.gms.models.UseEquipment;

import java.util.List;

/**
 * Created by lovedrose on 1/24/16.
 */
public interface UseEquipmentService extends IService<UseEquipment> {
    void useEquipments(MessageService messageService, EquipmentService equipmentService,Integer useId, Integer equipmentId, Double duration, Integer number);

    List<UseEquipment> findUserUseEquipments(Integer userId);
}
