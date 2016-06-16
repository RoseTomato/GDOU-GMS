package love.drose.gms.services;

import love.drose.gms.models.Equipment;
import love.drose.gms.utils.Page;

import java.util.List;

/**
 * Created by lovedrose on 1/20/16.
 */
public interface EquipmentService extends IService<Equipment> {
    Equipment findByNameWithTotalIsNotNull(String name);

    void addEquipment(Equipment equipment);

    Page findPageDataWithTotalIsNotNull(Integer pageNum, Integer pageSize);

    List<Equipment> findUsableEquipmentByName(String name);

    Object fetchEquipmentsWithCategoryId(Integer categoryId);

}
