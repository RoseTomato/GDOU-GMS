package love.drose.gms.controllers;

import love.drose.gms.models.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lovedrose on 12/25/15.
 */
@Controller
@RequestMapping("/messageHandler")
public class MessageHandler extends BaseHandler {

    /**
     * iOS端获取未读消息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/fetchMessage")
    public Object fetchMessage(Integer userId) {
        logger.debug("<== [userId:" + userId + "]");
        List<Message> messages = null;
        Map<String, Object> map = new HashMap<String, Object>();

        messages = messageService.findUserMessage(userId);

        if (messages == null) {
            map.put(RESULT, "");
        }
        map.put(RESULT, OK);
        map.put(DATA, messages);

        logger.debug("==>");
        return map;
    }

    /**
     * 获取用户未读消息
     * @param type
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fetchUserMessage", method = RequestMethod.POST)
    public Object fetchUserMessage(String type, Integer userId) {
        logger.debug("<== [type:" + type + "userId:" + userId + "]");
        List<Message> messages = null;
        Map<String, Object> map = new HashMap<String, Object>();

        messages = messageService.findUserMessageByType(type, userId);

        if (messages == null) {
            map.put(RESULT, "");
        }
        map.put(RESULT, OK);
        map.put(DATA, messages);

        logger.debug("==>");
        return map;
    }

    /**
     * 用户阅读消息
     * @param messageId
     * @return
     */
    @ResponseBody
     @RequestMapping("/readMessage")
     public Object readMessage(Integer messageId) {
        logger.debug("<== [messageId:" + messageId + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Message message = messageService.findById(messageId);
            message.setState("已读");
            messageService.update(message);

            map.put(RESULT, OK);
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }

        logger.debug("==>");
        return map;
    }

    /**
     * 降低消息级别
     * @param messageId
     * @return
     */
    @ResponseBody
    @RequestMapping("/lowerMessageRank")
    public Object lowerMessageRank(Integer messageId) {
        logger.debug("<== [messageId:" + messageId + "]");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Message message = messageService.findById(messageId);
            message.setRank("一般");
            messageService.update(message);

            map.put(RESULT, OK);
        } catch (Exception e) {
            map.put(RESULT, FAILURE);
            e.printStackTrace();
        }

        logger.debug("==>");
        return map;
    }
}
