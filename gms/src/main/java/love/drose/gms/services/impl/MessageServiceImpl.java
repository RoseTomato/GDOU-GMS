package love.drose.gms.services.impl;

import love.drose.gms.models.Message;
import love.drose.gms.models.User;
import love.drose.gms.services.MessageService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by lovedrose on 12/25/15.
 */
@Service("messageService")
public class MessageServiceImpl extends BaseService<Message> implements MessageService {

    @Override
    public List<Message> findUserMessage(Integer userId) {
        logger.debug("in <==");
        List<Message> messages = null;
        try {
            Example example = new Example(Message.class);
            Example.Criteria criteria = example.createCriteria();

            // 指定查询条件
            criteria.andEqualTo("state", "未读").andEqualTo("userId", userId);
            messages = selectByExample(example);
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            e.printStackTrace();
        }

        logger.debug("out ==>");
        return messages;
    }

    @Override
    public List<Message> findUserMessageByType(String type, Integer userId) {
        logger.debug("in <==");
        List<Message> messages = null;
        try {
            Example example = new Example(Message.class);
            Example.Criteria criteria = example.createCriteria();

            // 指定查询条件
            criteria.andEqualTo("state", "未读").andEqualTo("userId", userId).andEqualTo("type", type);
            messages = selectByExample(example);
        } catch (Exception e) {
            logger.error("error:"+e.getMessage());
            e.printStackTrace();
        }

        logger.debug("out ==>");
        return messages;
    }
}
