package love.drose.gms.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by lovedrose on 1/24/16.
 */
public class UseEquipment {
    /**
     * 编号.
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 创建记录的时刻
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    /**
     * 开始的时刻
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startTime;

    /**
     * 使用（预约）时长
     */
    private Double duration;

    /**
     * 使用者id
     */
    private Integer userId;

    /**
     * 场地id
     */
    private Integer equipmentId;

    /**
     * 使用还是预约
     */
    private Integer appoint;

    /**
     * 状态：有效，失效
     */
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getAppoint() {
        return appoint;
    }

    public void setAppoint(Integer appoint) {
        this.appoint = appoint;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public UseEquipment() {
    }

    public UseEquipment(Integer id, Date createTime, Date startTime, Double duration, Integer userId, Integer equipmentId, Integer appoint, String state) {
        this.id = id;
        this.createTime = createTime;
        this.startTime = startTime;
        this.duration = duration;
        this.userId = userId;
        this.equipmentId = equipmentId;
        this.appoint = appoint;
        this.state = state;
    }

    @Override
    public String toString() {
        return "UseEquipment{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", userId=" + userId +
                ", equipmentId=" + equipmentId +
                ", appoint=" + appoint +
                ", state='" + state + '\'' +
                '}';
    }
}
