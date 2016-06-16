package love.drose.gms.taskes;

import love.drose.gms.models.Message;
import love.drose.gms.models.UseEquipment;
import love.drose.gms.models.UseField;
import love.drose.gms.services.MessageService;
import love.drose.gms.services.UseEquipmentService;
import love.drose.gms.services.UseFieldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by lovedrose on 2/4/16.
 */
public class UseEquipmentTimeOutTask extends TimerTask {
    Logger logger = LogManager.getLogger(UseFieldTimeOutTask.class);

    private UseEquipmentService useEquipmentService;
    private MessageService messageService;

    /**
     * 一小时的毫秒数
     */
    private final long  ONE_HOUR_MSEL = 3600000;

    ServletContextEvent servletContextEvent;

    public UseEquipmentTimeOutTask(ServletContextEvent servletContextEvent) {
        this.servletContextEvent = servletContextEvent;
    }

    @Override
    public void run() {
        logger.debug("<== start task");
        try {
            WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.servletContextEvent.getServletContext());
            useEquipmentService = (UseEquipmentService) context.getBean("useEquipmentService");
            messageService = (MessageService) context.getBean("messageService");

            // 遍历use_field表
            List<UseEquipment> useEquipments = useEquipmentService.findAllByProperty("state", "有效");
            if (useEquipments != null && useEquipments.size() > 0 ) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date endTime = new Date();
                for (UseEquipment useEquipment : useEquipments) {
                    // 按时的，超过使用时长就设置无效
                    if (useEquipment.getStartTime() != null) {
                        Date startTime = useEquipment.getStartTime();
                        double duration = useEquipment.getDuration();
                        long targetTime = startTime.getTime() + (long)(duration * ONE_HOUR_MSEL);
                        // 当使用时间结束了，设置state状态为无效
                        if (endTime.getTime() - targetTime > 0) {
                            useEquipment.setState("无效");
                            useEquipmentService.update(useEquipment);
                        }

                        // 若使用时间到了当前时间6秒范围内，添加一条即将到点消息
                        if ((targetTime - endTime.getTime()) < 3000 && (targetTime - endTime.getTime()) > 0
                                || (endTime.getTime() - targetTime) < 3000 && (endTime.getTime() - targetTime) > 0) {
                            // 添加一条租用场地消息
                            Message message = new Message();
                            message.setState("未读");
                            message.setUserId(useEquipment.getUserId());
                            message.setType("场地");
                            message.setRank("重要");
                            message.setTitle("您有一条租用场地消息");
                            message.setContent("您有一个租用的场地即将到期");
                            messageService.save(message);
                        }
                    } else { // 按次使用的， 超过当天就无效了
                        String strEndDate = sdf.format(endTime);
                        String strCreateDate = sdf.format(useEquipment.getCreateTime());
                        Date endDate = sdf.parse(strEndDate);
                        Date createDate = sdf.parse(strCreateDate);

                        if (endDate.after(createDate)) {
                            useEquipment.setState("无效");
                            useEquipmentService.update(useEquipment);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("==> task failure.");
            e.printStackTrace();
        }
        logger.debug("==> finish task.");
    }
}
