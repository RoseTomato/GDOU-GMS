package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by lovedrose on 2/21/16.
 */
public class Competition {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 器材名.
     */
    private String name;

    /**
     * 描述.
     */
    private String description;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 记录时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private String state;
    /**
     * 地点
     */
    private String place;
    /**
     * 申请人
     */
    private String applyer;
    /**
     * 赞助商
     */
    private String sponsor;
    /**
     * 规模
     */
    private Integer scale;
    /**
     * 热度
     */
    private Double star;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                ", state='" + state + '\'' +
                ", place='" + place + '\'' +
                ", applyer='" + applyer + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", scale=" + scale +
                ", star=" + star +
                ", userId=" + userId +
                '}';
    }

    public Competition() {
    }

    public Competition(Integer id, String name, String description, Date startTime, Date endTime, Date createTime, String state, String place, String applyer, String sponsor, Integer scale, Double star, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
        this.state = state;
        this.place = place;
        this.applyer = applyer;
        this.sponsor = sponsor;
        this.scale = scale;
        this.star = star;
        this.userId = userId;
    }
}
