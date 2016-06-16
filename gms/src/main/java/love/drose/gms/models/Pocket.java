package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lovedrose on 4/14/16.
 */
public class Pocket {
    @Id
    @Column(name = "Id")
    private Integer id;

    private Integer userId;

    private Double money;

    @Override
    public String toString() {
        return "Pocket{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                '}';
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Pocket() {

    }

    public Pocket(Integer id, Integer userId, Double money) {

        this.id = id;
        this.userId = userId;
        this.money = money;
    }
}
