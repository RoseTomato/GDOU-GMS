package love.drose.gms.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回J-UI客户端json数据的工具类
 * Created by lovedrose on 2015/11/21.
 */
public class DWZJsonUtil {
    static Map<String, String> map = new HashMap<String, String>();

    /**
     * 初始化map.
     *
     * @return
     */
    private static Map<String, String> getMap() {
        map.put("statusCode", "");// 状态码，200-成功，300-失败，301-超时
        map.put("message", "");// 携带信息，与用户交互
        map.put("navTabId", "");// 需要刷新面板的rel
        map.put("callbackType", "");// callbackType如果是closeCurrent就会关闭当前tab只有callbackType="forward"时需要forwardUrl值如果表单提交只提示操作是否成功,
        // 就可以不指定回调函数.
        map.put("forwardUrl", "");// forword的url地址
        map.put("rel", "");//
        return map;
    }

    /**
     * 操作成功
     *
     * @return
     */
    public static Map<String, String> success() {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", "操作成功！");
        return map;
    }

    /**
     * 操作成功并携带消息
     *
     * @param msg
     * @return
     */
    public static Map<String, String> success(String msg) {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", msg);
        return map;
    }

    /**
     * 操作成功，并刷新指定页面
     *
     * @param msg
     * @param navTabId
     * @return
     */
    public static Object success(String msg, String navTabId) {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", msg);
        map.put("navTabId", navTabId);
        return map;
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static Object error() {
        map = getMap();
        map.put("statusCode", "300");
        map.put("message", "操作失败");
        return map;
    }

    /**
     * 操作失败
     *
     * @return
     */
    public static Object errorByException() {
        map = getMap();
        map.put("statusCode", "300");
        map.put("message", "操作失败,系统异常！");
        return map;
    }

    /**
     * 操作失败，返回指定消息
     *
     * @param msg
     * @return
     */
    public static Map<String ,String> error(String msg) {
        map = getMap();
        map.put("statusCode", "300");
        map.put("message", msg);
        return map;
    }

    // public static JsonUtil refresh(String refreshNavTabId) { }

    /**
     * 操作成功，关闭当前页并刷新指定页面
     * @param refreshNavTabId
     * @return
     */
    public static Map<String, String> closeCurrentAndRefresh(String refreshNavTabId) {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", "操作成功");
        map.put("navTabId", refreshNavTabId);
        map.put("callbackType", "closeCurrent");
        return map;
    }

    /**
     * 操作成功并刷新指定页面
     * @param refreshNavTabId
     * @return
     */
    public static Map<String, String> successAndRefresh(String refreshNavTabId) {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", "操作成功");
        map.put("navTabId", refreshNavTabId);
        return map;
    }

    /**
     * 操作成功并关闭当前页面
     * @return
     */
    public static Map<String, String> successAndCloseCurrent() {
        map = getMap();
        map.put("statusCode", "200");
        map.put("message", "操作成功");
        map.put("callbackType", "closeCurrent");
        return map;
    }

    /**
     * 会话超时.
     *
     * @return
     */
    public static Object sessionTimeOut() {
        map = getMap();
        map.put("statusCode", "301");
        map.put("message", "会话超时，请重新登录");
        return map;
    }
}
