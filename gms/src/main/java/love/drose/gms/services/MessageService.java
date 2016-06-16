package love.drose.gms.services;

import love.drose.gms.models.Message;

import java.util.List;

/**
 * Created by lovedrose on 12/25/15.
 */
public interface MessageService extends IService<Message> {

    List<Message> findUserMessage(Integer userId);

    List<Message> findUserMessageByType(String type, Integer userId);
}
