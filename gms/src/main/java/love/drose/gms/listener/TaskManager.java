package love.drose.gms.listener;

import love.drose.gms.taskes.UseEquipmentTimeOutTask;
import love.drose.gms.taskes.UseFieldTimeOutTask;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

/**
 * Created by lovedrose on 12/21/15.
 */
public class TaskManager implements ServletContextListener {

    /**
     * 无延迟
     */
    public static final long NO_DELAY = 0;
    /**
     * 定时器
     */
    private Timer fieldTimer;
    private Timer equipmentTimer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        fieldTimer = new Timer("useFieldTimeOutTask", true);
        fieldTimer.schedule(new UseFieldTimeOutTask(servletContextEvent), NO_DELAY, 5000);

        equipmentTimer = new Timer("useEquipmentTimeOutTask", true);
        equipmentTimer.schedule(new UseEquipmentTimeOutTask(servletContextEvent), NO_DELAY, 5000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
