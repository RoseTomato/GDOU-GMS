package love.drose.gms.services.impl;

import com.github.pagehelper.PageHelper;
import love.drose.gms.models.Equipment;
import love.drose.gms.models.Message;
import love.drose.gms.services.EquipmentService;
import love.drose.gms.utils.Page;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lovedrose on 1/20/16.
 */
@Service("equipmentService")
public class EquipmentServiceImpl extends BaseService<Equipment> implements EquipmentService {
    @Override
    public List<Equipment> findUsableEquipmentByName(String name) {
        logger.debug("<== [name:" + name + "]");
        List<Equipment> equipments = null;
        try {
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("name", name);
            criteria.andEqualTo("state", "可使用");

            equipments = selectByExample(example);

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
        return equipments;
    }

    @Override
    public Equipment findByNameWithTotalIsNotNull(String name) {
        logger.debug("<==");
        Equipment equipment = null;
        try {
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();

            // 指定查询条件
            criteria.andEqualTo("name", name).andIsNotNull("total");
            List<Equipment> list = selectByExample(example);
            if (list != null && list.size() > 0) {
                equipment = list.get(0);
            }

        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            e.printStackTrace();
        }
        logger.debug("==>");
        return equipment;
    }

    @Override
    public void addEquipment(Equipment equipment) {
        int total = equipment.getTotal();
        // 先查找有没有同名器材，有则修改其total
        Equipment e = findByNameWithTotalIsNotNull(equipment.getName());
        if (e != null) {
            // 更新费用
            e.setFee(equipment.getFee());

            e.setTotal(e.getTotal() + equipment.getTotal());
            // 更新
            update(e);

            for (int i = 0; i < total; i++) {

                // 置空当前要添加的器材总数
                equipment.setTotal(null);
                // 设置默认图片
                equipment.setImage("defaultImage");
                // 设置默认状态
                equipment.setState("可使用");
                // 保存
                save(equipment);

            }
        } else {
            for (int i = 0; i < total; i++) {
                if (i != 0) {
                    // 置空当前要添加的器材总数
                    equipment.setTotal(null);
                } else {
                    equipment.setCurrentNumber(0);
                }
                // 设置默认图片
                equipment.setImage("defaultImage");
                // 设置默认状态
                equipment.setState("可使用");
                // 保存
                save(equipment);

            }
        }
    }

    @Override
    public Page findPageDataWithTotalIsNotNull(Integer pageNum, Integer pageSize) {
        logger.debug("<==[pageNum:" +pageNum + ", pageSize:" + pageSize );
        try {
            // 查询出模型的总记录数
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();

            // 指定查询not null的字段
            criteria.andIsNotNull("total");

            // 查询总记录数
            Integer totalRecord = mapper.selectCountByExample(example);

            // 封装到Page对象里面
            Page page = new Page(pageSize, pageNum, totalRecord);

            // 获取模型的分页list集合
            // 分页查询
            PageHelper.startPage(pageNum, pageSize);
            List<Equipment> list = selectByExample(example);

            if (list.size() > 0) {
                page.setList(list);

                logger.debug("==> [page:" + page + "]");
                return page;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==> null");
        return null;
    }

    @Override
    public List<Equipment> fetchEquipmentsWithCategoryId(Integer categoryId) {
        logger.debug("<== [categoryId:" + categoryId + "]");
        List<Equipment> equipments = null;
        try {
            Example example = new Example(Equipment.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("categoryId", categoryId);
            criteria.andEqualTo("state", "可使用");
            criteria.andIsNotNull("total");

            equipments = selectByExample(example);

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
        return equipments;
    }
}
