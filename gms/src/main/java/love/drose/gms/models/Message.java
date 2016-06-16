package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lovedrose on 12/25/15.
 */
public class Message {

    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息类型：系统，场地，赛事，器材
     */
    private String type;
    /**
     * 消息阅读状态：未读，已读
     */
    private String state;
    /**
     * 消息级别：一般，重要
     */
    private String rank;
    /**
     * 用户Id，0则为所有用户
     */
    private Integer userId;

    public Message() {
    }

    public Message(Integer id, String title, String content, String type, String state, String rank, Integer userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.type = type;
        this.state = state;
        this.rank = rank;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", rank='" + rank + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
