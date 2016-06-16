package love.drose.gms.services.impl;

import javafx.scene.shape.Circle;
import love.drose.gms.models.Equipment;
import love.drose.gms.models.Message;
import love.drose.gms.models.UseEquipment;
import love.drose.gms.services.EquipmentService;
import love.drose.gms.services.MessageService;
import love.drose.gms.services.UseEquipmentService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Created by lovedrose on 1/24/16.
 */
@Service("useEquipmentService")
public class UseEquipmentServiceImpl extends BaseService<UseEquipment> implements UseEquipmentService {
    @Override
    public void useEquipments(MessageService messageService, EquipmentService equipmentService, Integer userId, Integer equipmentId, Double duration, Integer number) {
        logger.debug("<== [service:" + equipmentService + "]");
        try {
            // 根据id获取器材
            Equipment equipment = equipmentService.findById(equipmentId);
            if (equipment != null
                    && number <= (equipment.getTotal() - equipment.getCurrentNumber())) {
                // 先从剩余器材里面，查出同名的器材
                List<Equipment> equipments = equipmentService.findUsableEquipmentByName(equipment.getName());
                // 循环number次，修改属性，并添加到use_equipment表
                for (int i = 0; i < number; i++) {
                    Equipment tmp = equipments.get(i);
                    // 设置
                    tmp.setState("使用中");
                    tmp.getId();
                    // 添加
                    UseEquipment useEquipment = new UseEquipment();
                    useEquipment.setCreateTime(new Date());
                    useEquipment.setUserId(userId);
                    useEquipment.setEquipmentId(equipmentId);
                    useEquipment.setAppoint(0);
                    useEquipment.setState("有效");
                    // 判断何种租用类型
                    if (equipment.getUseType().equals("按次")) {
                        // 按次无需设置
                    } else {
                        useEquipment.setStartTime(new Date());
                        useEquipment.setDuration(duration);
                    }

                    save(useEquipment);
                }

                // 更新
                equipment.setCurrentNumber(equipment.getCurrentNumber() + number);
                equipmentService.update(equipment);

                // 添加一条租用场地消息
                Message message = new Message();
                message.setState("未读");
                message.setUserId(userId);
                message.setType("器材");
                message.setRank("一般");
                message.setTitle("您有一条租用器材消息");
                message.setContent("您在 "+new Date()+" 租用了" + number + "个" + equipment.getName() + "，" +
                        "祝您玩的开心");
                messageService.save(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
    }

    @Override
    public List<UseEquipment> findUserUseEquipments(Integer userId) {
        logger.debug("<==");
        try {
            Example example = new Example(UseEquipment.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("state", "有效");
            criteria.andEqualTo("userId", userId);
            criteria.andEqualTo("appoint", 0);

            logger.debug("==>");
            return selectByExample(example);
        } catch (Exception e) {
            logger.error("==> error:" + e.getMessage());
            e.printStackTrace();
        }


        logger.debug("==>");
        return null;
    }
}
