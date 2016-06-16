package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lovedrose on 4/7/16.
 */
public class SportStar {
    /**
     * 编号.
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    private String name;

    @Column(name = "user_id")
    private Integer userId;

    private Integer score;

    private String head;



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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public SportStar() {

    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "SportStar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", score=" + score +
                ", head='" + head + '\'' +
                '}';
    }

    public SportStar(Integer id, String name, Integer userId, Integer score, String head) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.score = score;
        this.head = head;
    }
}
