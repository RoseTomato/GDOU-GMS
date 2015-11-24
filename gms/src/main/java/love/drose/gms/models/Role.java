package love.drose.gms.models;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 角色模型
 * Created by lovedrose on 2015/11/18.
 */
@Component
public class Role {
    /**
     * 编号
     */
    @Id
    @Column(name = "Id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 角色名
     */
    private String name;

    private Integer managerId;

    private String description;

    public Role(Integer id, String name, Integer managerId, String description) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.description = description;
    }

    /**
     * 默认无参构造函数
     */
    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                ", description='" + description + '\'' +
                '}';
    }

    /** ======================= setter and getter ================================= */

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

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
