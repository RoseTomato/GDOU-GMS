package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by lovedrose on 4/8/16.
 */
public class Activity {
    @Id
    @Column(name = "Id")
    private Integer id;

    private String name;
    private String content;
    private Date startTime;
    private Date endTime;
    private String state;

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Activity() {

    }

    public Activity(Integer id, String name, String content, Date startTime, Date endTime, String state) {

        this.id = id;
        this.name = name;
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = state;
    }
}
