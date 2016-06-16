package love.drose.gms.utils;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.exceptions.InvalidDeviceTokenFormatException;
import javapns.notification.*;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;
import javapns.org.json.JSONException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lovedrose on 3/13/16.
 */
public class APNSUtil {

    /**
     * log对象
     */
    public static Logger logger = LogManager.getLogger(APNSUtil.class);

    /**
     * 证书路径
     */
    private static String keystore = null;
    /**
     * 证书密码
     */
    private static  String certPassword = null;
    /**
     * true：production， false：sandbox
     */
    private static Boolean isProduction = false;
    /**
     * 推送声音（默认）
     */
    public static final String DEFAULT_SOUND = "default";
    /**
     * 线程数量
     */
    private static final int THREAD_COUNT = 10;
    /**
     * 线程监听
     */
    private static final NotificationProgressListener DEBUGGING_PROGRESS_LISTENER = new NotificationProgressListener() {
        public void eventThreadStarted(NotificationThread notificationThread) {
            System.out.println("   [EVENT]: thread #" + notificationThread.getThreadNumber() + " started with " + " devices beginning at message id #" + notificationThread.getFirstMessageIdentifier());
        }
        public void eventThreadFinished(NotificationThread thread) {
            System.out.println("   [EVENT]: thread #" + thread.getThreadNumber() + " finished: pushed messages #" + thread.getFirstMessageIdentifier() + " to " + thread.getLastMessageIdentifier() + " toward "+ " devices");
        }
        public void eventConnectionRestarted(NotificationThread thread) {
            System.out.println("   [EVENT]: connection restarted in thread #" + thread.getThreadNumber() + " because it reached " + thread.getMaxNotificationsPerConnection() + " notifications per connection");
        }
        public void eventAllThreadsStarted(NotificationThreads notificationThreads) {
            System.out.println("   [EVENT]: all threads started: " + notificationThreads.getThreads().size());
        }
        public void eventAllThreadsFinished(NotificationThreads notificationThreads) {
            System.out.println("   [EVENT]: all threads finished: " + notificationThreads.getThreads().size());
        }
        public void eventCriticalException(NotificationThread notificationThread, Exception exception) {
            System.out.println("   [EVENT]: critical exception occurred: " + exception);
        }
    };

    static {
        Properties properties = new Properties();
        InputStream inputStream;

        try {
            inputStream = APNSUtil.class
                    .getClassLoader()
                    .getResourceAsStream("push.properties");
            properties.load(inputStream);
            String certPath = properties.getProperty("keystore");
            File file = new File(certPath);
            keystore = file.getAbsolutePath();
            certPassword = properties.getProperty("certPassword");
            isProduction = Boolean.valueOf(properties.getProperty("isProduction", "false"));
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简单消息推送
     * @param deviceToken
     * @param alertMsg
     */
    public static void pushMessageNotification(String deviceToken, String alertMsg) {
        try {
            Push.alert(alertMsg,keystore,certPassword,isProduction,deviceToken);
        } catch (CommunicationException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * 推送一个标记
     * @param deviceToken
     * @param badge
     */
    public static void pushBadgeNotification(String deviceToken, int badge) {
        logger.debug("<== [deviceToken:"+deviceToken+", badge:" + badge + "]");
        try {
            Push.badge(badge, keystore, certPassword, isProduction,deviceToken);
        } catch (CommunicationException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    /**
     * 推送一个声音
     * @param deviceToken
     * @param sound
     */
    public static void pushSoundNotification(String deviceToken, String sound) {
        logger.debug("<== [deviceToken:"+deviceToken+", sound:" + sound + "]");
        try {
            Push.sound(sound, keystore, certPassword, isProduction, deviceToken);
        } catch (CommunicationException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    /**
     * 推送一个alert ＋ badge ＋ sound 通知
     * @param deviceToken - 设备标识
     * @param alertMsg - 消息
     * @param badge - 标识数
     * @param sound - 通知声音
     */
    public static void pushCombinedNotification(String deviceToken, String alertMsg, int badge, String sound) {
        logger.debug("<== [deviceToken:" + deviceToken + ", alertMsg:" + alertMsg + ", badge:" + badge + "]");
        try {
            Push.combined(alertMsg, badge, sound, keystore, certPassword, isProduction, deviceToken);
        } catch (CommunicationException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    /**
     * 推送自定义负载
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws JSONException
     * @throws CommunicationException
     * @throws KeystoreException
     */
    public static void pushPayload(List<Device> devices, String msg, int badge, String sound, Map<String, String> map) {
        logger.debug("<== [devices:" + devices + ", msg:" + msg + ", badge:" + badge + ", sound:" + sound + ", map:" + map + "]");
        try {
            PushNotificationPayload payload = customPayload(msg, badge, sound, map);
            Push.payload(payload, keystore, certPassword, isProduction, devices);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (KeystoreException e) {
            e.printStackTrace();
        } catch (CommunicationException e) {
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    /**
     * 用内置线程推送负载信息
     * @param devices
     * @param msg
     * @param badge
     * @param sound
     * @param map
     * @throws Exception
     */
    public static void pushPayLoadByThread(List<Device> devices, String msg,Integer badge,String sound,Map<String,String> map) throws Exception{
        PushNotificationPayload payload = customPayload(msg, badge, sound, map);
        Push.payload(payload, keystore, certPassword, isProduction, THREAD_COUNT, devices);
    }

    /**
     * 自定义负载
     * @param msg
     * @param badge
     * @param sound
     * @param map 自定义字典
     * @return
     * @throws JSONException
     */
    private static PushNotificationPayload customPayload(String msg,Integer badge,String sound,Map<String,String> map) throws JSONException{
        PushNotificationPayload payload = PushNotificationPayload.complex();
            payload.addAlert(msg);
        if(badge != null){
            payload.addBadge(badge);
        }
        payload.addSound(sound);
        if(map!=null && !map.isEmpty()){
            Object[] keys = map.keySet().toArray();
            Object[] vals = map.values().toArray();
            if(keys!= null && vals != null && keys.length == vals.length){
                for (int i = 0; i < map.size(); i++) {
                    payload.addCustomDictionary(String.valueOf(keys[i]),String.valueOf(vals[i]));
                }
            }
        }
        return payload;
    }


    /**
     * 自定义负载推送（多线程）
     * @param deviceToken
     * @param alertMsg
     * @param badge
     */
    public static void threadPushPayloadNotification(String deviceToken, String alertMsg, int badge) {
        logger.debug("<== [deviceToken:" + deviceToken + ", alertMsg:" + alertMsg + ", badge:" + badge + "]");
        try {
            // 简历与Apple服务器连接
            AppleNotificationServer server = new AppleNotificationServerBasicImpl(keystore, certPassword, isProduction);
            List<PayloadPerDevice> payloadPerDevices = new ArrayList<PayloadPerDevice>();
            PushNotificationPayload payload = new PushNotificationPayload();
            payload.addAlert(alertMsg);
            payload.addSound(DEFAULT_SOUND);
            payload.addBadge(badge);

            // 将要推送的消息和手机唯一标识绑定
            PayloadPerDevice pay = new PayloadPerDevice(payload, deviceToken);
            payloadPerDevices.add(pay);

            NotificationThreads work = new NotificationThreads(server, payloadPerDevices, THREAD_COUNT);
            // 对线程的监听（务必加上）
            work.setListener(DEBUGGING_PROGRESS_LISTENER);
            // 启动线程
            work.start();
            // 等待所有线程启动完成
            work.waitForAllThreads();

        } catch (KeystoreException e) {
            logger.error("==> [error:" + e.getMessage() + "]");
            e.printStackTrace();
        } catch (JSONException e) {
            logger.error("==> [error:" + e.getMessage() + "]");
            e.printStackTrace();
        } catch (InvalidDeviceTokenFormatException e) {
            logger.error("==> [error:" + e.getMessage() + "]");
            e.printStackTrace();
            logger.error("==> [error:" + e.getMessage() + "]");
        } catch (InterruptedException e) {
            logger.error("==> [error:" + e.getMessage() + "]");
            e.printStackTrace();
        }
        logger.debug("==>");
    }

    public static void main(String[] args) {
        String token = "d14d6bf0f48203a2fd0de620bf1071e6e8f4640a2d3832775afefa1f1b8bd24a";// 手机唯一标识
        pushMessageNotification(token, "测试");
//        pushBadgeNotification(token, 2);
//        pushSoundNotification(token, DEFAULT_SOUND);

        System.out.println("push over!");
    }

}
