package love.drose.gms.services.impl;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import love.drose.gms.models.Field;
import love.drose.gms.models.Message;
import love.drose.gms.models.UseField;
import love.drose.gms.services.FieldService;
import love.drose.gms.services.MessageService;
import love.drose.gms.services.UseFieldService;
import love.drose.gms.utils.APNSUtil;
import love.drose.gms.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by Administrator on 2015/12/10.
 */
@Service("useFieldService")
public class UseFieldServiceImpl extends BaseService<UseField> implements UseFieldService {

    @Autowired
    private MessageService messageService;

    @Override
    public String useField(Field field, Integer userId, Double duration) {
        logger.debug("<== [field:" + field + ", userId:" + userId+", duration:" + duration + "]");
        // 先判断场地状态
        if ("可使用".equals(field.getState())) {
            Date now = new Date();
            // 检查该时间段是否被预约了
            Example example = new Example(UseField.class);
            Example.Criteria criteria = example.createCriteria();

            // 查询该id的场地正在被使用
            criteria.andEqualTo("state", "有效");
            criteria.andEqualTo("fieldId", field.getId());
            criteria.andEqualTo("appoint", 1);
            List<UseField> useFields = selectByExample(example);
            boolean canUse = true;     // 能否被使用
            if (useFields != null) {
                for (UseField useField : useFields) {
                    // 获取预约的开始时间与时长
                    Date afStartTime = useField.getStartTime();
                    Double afDuration = useField.getDuration();
                    // 计算预约的结束时间
                    Calendar afEndTime = Calendar.getInstance();
                    afEndTime.setTime(afStartTime);
                    int mins = (int)(afDuration * 60);
                    afEndTime.add(Calendar.MINUTE, mins);

                    // 计算使用结束时间 ＝ 此刻时间＋时长
                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(now);
                    endTime.add(Calendar.MINUTE, (int)(duration * 60));

                    // 若按次使用的，则此刻 时间 ＋ 时长 不能在预约开始时间之后
                    if ("按次".equals(field.getUseType())) {
                        if (endTime.after(afStartTime)) {
                            canUse = false;
                        }
                    } else {    // 若按时使用，则：此刻 ＋ 时长只能在预约开始时间之前，或者此刻只能在预约结束时间之后
                        if (endTime.before(afStartTime) || afEndTime.before(now)) {
                            canUse = true;
                        } else {
                            canUse = false;
                        }
                    }
                }
            }

            // 判断人数是否已满
            if ((field.getCurrentNumber() < field.getGalleryful()) && canUse) {
                UseField useField = new UseField();
                useField.setAppoint(0); // 非预约
                useField.setCreateTime(now); // 创建时间
                useField.setState("有效");
                useField.setUserId(userId);
                useField.setFieldId(field.getId());
                // 判断使用类型
                if ("按时".equals(field.getUseType())) {
                    // 设置开始时间
                    useField.setStartTime(new Date());
                    useField.setDuration(duration);
                } else {
                    // 按次使用则需把duration设置为0
                    useField.setDuration(0.0);
                }

                save(useField);

                // 添加一条租用场地消息
                Message message = new Message();
                message.setState("未读");
                message.setUserId(userId);
                message.setType("场地");
                message.setRank("一般");
                message.setTitle("您有一条租用场地消息");
                message.setContent("您在 "+DateUtil.convertDateToString(DateUtil.DATETIME, useField.getCreateTime())+" 租用了一个" + field.getName() + "，" +
                        "祝您玩的开心");
                messageService.save(message);
            } else {
                logger.debug("==> 人数已满");
                return "人数已满";
            }
        } else {
            logger.debug("==> 场地不可使用");
            return "场地不可使用";
        }
        logger.debug("==>");
        return "ok";
    }

    @Override
    public List<UseField> findUserUseField(Integer userId) {
        logger.debug("<==  ");

        try {
            Example example = new Example(UseField.class);
            Example.Criteria criteria = example.createCriteria();

            criteria.andEqualTo("userId", userId)
                    .andEqualTo("state", "有效")
                    .andEqualTo("appoint", 0);

            logger.debug("==>");
            return selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("==>");
        return null;
    }

    @Override
    public Boolean appointField(Integer userId, Field field, String startTime, Double duration) {
        logger.debug("<==");
        Boolean result;
        boolean isCrash = false;    // 是否有时间冲突
        try {
            // 计算预约结束时间
            Date st = DateUtil.convertStringToDate(DateUtil.DATETIME, startTime);
            Calendar appointEndTime = Calendar.getInstance();
            appointEndTime.setTime(st);
            int min = (int)(duration * 60);
            appointEndTime.add(Calendar.MINUTE, min);

            // 转换预约开始时间
            Date afStartTime = DateUtil.convertStringToDate(DateUtil.DATETIME, startTime);

            Example example = new Example(UseField.class);
            Example.Criteria criteria = example.createCriteria();

            // 查询该id的场地正在被使用
            criteria.andEqualTo("state", "有效");
            criteria.andEqualTo("fieldId", field.getId());
            List<UseField> useFields = selectByExample(example);
            // 若场地已经被使用
            if (useFields != null) {
                for (UseField useField : useFields) {
                    // 获取场地的开始时间
                    Date ufStartTime = useField.getStartTime();
                    boolean isBeforeEndTime = appointEndTime.after(ufStartTime);
                    boolean isAfterStartTime = afStartTime.before(ufStartTime);
                    //  场地使用的开始时间不能夹在预约的开始时间和结束中间
                    if (isBeforeEndTime && isAfterStartTime) {
                        isCrash = true;
                    }

                    // 按次使用的无需计算结束时间
                    if (duration != 0.0) {
                        // 计算使用场地的结束时间
                        Calendar ufEndTime = Calendar.getInstance();
                        ufEndTime.setTime(useField.getStartTime());
                        int minutes = (int)(useField.getDuration() * 60);
                        ufEndTime.add(Calendar.MINUTE, minutes);

                        // 场地使用的结束时间不能在预约开始间之后
                        boolean isAfter = ufEndTime.after(afStartTime);
                        if (isAfter) {
                            isCrash = true;
                        }
                    }
                }
            } else {
                // 场地没有被使用
                isCrash = false;
            }

            if (isCrash) {
                result = false;
            } else {
                // 没有时间冲突，可以添加预约记录
                UseField useField = new UseField();
                useField.setUserId(userId);
                useField.setFieldId(field.getId());
                useField.setState("有效");
                useField.setDuration(duration);
                useField.setStartTime(afStartTime);
                useField.setCreateTime(new Date());
                useField.setAppoint(1); // 1为预约
                // 保存
                save(useField);

                // 添加一条预约场地消息
                Message message = new Message();
                message.setState("未读");
                message.setUserId(userId);
                message.setType("场地");
                message.setRank("一般");
                message.setTitle("您有一条预约场地的消息");
                message.setContent("您在 " + DateUtil.convertDateToString(DateUtil.DATETIME, useField.getCreateTime()) + " 预约了一个" + field.getName() + "，" +
                        "祝您在" + DateUtil.convertDateToString(DateUtil.DATETIME, useField.getStartTime()) + "开心娱乐" + useField.getDuration() + "小时");
                messageService.save(message);

                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        logger.debug("==>");
        return result;
    }

    @Override
    public List<Field> fecthUserAppointField(Integer userId, FieldService fieldService) {
        logger.debug("<== [userId:"+userId+"]");
        List<Field> fields = null;
        try {
            Example example = new Example(UseField.class);
            Example.Criteria criteria = example.createCriteria();

            // 查询条件：用户id、appoint字段、state
            criteria.andEqualTo("userId", userId);
            criteria.andEqualTo("state", "有效");
            criteria.andEqualTo("appoint", 1);

            List<UseField> useFields = selectByExample(example);
            if (useFields != null) {
                fields = new ArrayList<Field>();
                for (UseField useField : useFields) {
                    Field field = fieldService.findById(useField.getFieldId());
                    fields.add(field);
                }
            }
        } catch (Exception e) {
            logger.error("==> [error:" + e.getMessage() + "]");
            e.printStackTrace();
        }
        logger.debug("==>");
        return fields;
    }
}
