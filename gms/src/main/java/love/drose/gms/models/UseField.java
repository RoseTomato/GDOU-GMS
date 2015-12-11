package love.drose.gms.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 使用的场地模型
 * Created by lovedrose on 2015/12/10.
 */
public class UseField {
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
    private Integer fieldId;

    /**
     * 使用还是预约
     */
    private Integer appoint;

    public UseField() {
    }

    @Override
    public String toString() {
        return "UseField{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", userId=" + userId +
                ", fieldId=" + fieldId +
                ", appoint=" + appoint +
                '}';
    }

    public UseField(Integer id, Date createTime, Date startTime, Double duration, Integer userId, Integer fieldId, Integer appoint) {
        this.id = id;
        this.createTime = createTime;
        this.startTime = startTime;
        this.duration = duration;
        this.userId = userId;
        this.fieldId = fieldId;
        this.appoint = appoint;
    }

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

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getAppoint() {
        return appoint;
    }

    public void setAppoint(Integer appoint) {
        this.appoint = appoint;
    }
}
