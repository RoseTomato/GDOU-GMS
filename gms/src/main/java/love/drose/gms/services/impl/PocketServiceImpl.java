package love.drose.gms.services.impl;

import love.drose.gms.models.Equipment;
import love.drose.gms.models.Field;
import love.drose.gms.models.Pocket;
import love.drose.gms.services.PocketService;
import love.drose.gms.services.UserService;
import love.drose.gms.utils.FinanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lovedrose on 4/14/16.
 */
@Service("pocketService")
public class PocketServiceImpl extends BaseService<Pocket> implements PocketService {

    @Override
    public Pocket findByUserId(Integer id) {
        logger.debug("<== [userId:" + id +"]");
        Example example = new Example(Pocket.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("userId", id);

        List<Pocket> pocketList = selectByExample(example);
        if (pocketList != null) {
            return pocketList.get(0);
        }
        logger.debug("==>");
        return null;
    }

    @Override
    public Boolean canPurchaseField(Integer userId, Field field, Double duration) {
        Boolean result = false;
        Double fee = field.getFee();
        // 判断场地类型
        if (field.getUseType().equals("按时")) {
            fee = fee * duration;
        }

        if (findByUserId(userId).getMoney() >= fee) {
            result = true;
        }
        return result;
    }

    @Override
    public void cutField(Integer userId, Field field, Double duration) {
        logger.debug("<== [userId:"+userId+", field:" + field + ", duration:" + duration + "]");
        Pocket pocket = findByUserId(userId);
        pocket.setMoney(pocket.getMoney() - field.getFee() * duration);
        update(pocket);
        // 添加营业额
        FinanceUtil.setCurDayTurnover(FinanceUtil.getCurDayTurnover() + field.getFee() * duration);
        logger.debug("==>");
    }

    @Override
    public void cutEquipment(Integer userId, Equipment equipment, Double duration, Integer number) {
        logger.debug("<== [userId:"+userId+", equipment:" + equipment + ", duration:" + duration + "]");
        if (equipment.getUseType().equals("按次")){
            Pocket pocket = findByUserId(userId);
            pocket.setMoney(pocket.getMoney() - equipment.getFee() * number);
            update(pocket);

            // 添加营业额
            FinanceUtil.setCurDayTurnover(FinanceUtil.getCurDayTurnover() + equipment.getFee() * number);

        } else {
            Pocket pocket = findByUserId(userId);
            pocket.setMoney(pocket.getMoney() - equipment.getFee() * duration *  number);
            update(pocket);

            // 添加营业额
            FinanceUtil.setCurDayTurnover(FinanceUtil.getCurDayTurnover() + equipment.getFee() * duration * number);
        }

        logger.debug("==>");
    }

    @Override
    public boolean canPurchaseEquipment(Integer userId, Equipment equipment, Double duration) {
        Boolean result = false;
        Double fee = equipment.getFee();
        if (findByUserId(userId).getMoney() >= fee) {
            result = true;
        }
        return result;
    }
}
