package love.drose.gms.models;

import java.util.Date;

/**
 * 充值记录
 * Created by lovedrose on 6/15/16.
 */
public class Recharge {
    private Integer id;
    private Integer userId;
    private String username;
    private Double rechargeMoney;
    private Date createTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Recharge() {
    }

    @Override
    public String toString() {
        return "Recharge{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", rechargeMoney=" + rechargeMoney +
                ", createTime=" + createTime +
                '}';
    }

    public Recharge(Integer id, Integer userId, String username, Double rechargeMoney, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.rechargeMoney = rechargeMoney;
        this.createTime = createTime;
    }
}
