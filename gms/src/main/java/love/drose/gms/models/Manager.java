package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 管理员模型
 * Created by lovedrose on 2015/11/19.
 */
public class Manager {
    @Id
    @Column(name = "Id")
    private Integer id;

    private String name;
    private String password;

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Manager() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
