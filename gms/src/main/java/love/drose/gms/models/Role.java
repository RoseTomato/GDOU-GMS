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

    /**
     * 构造函数
     * @param id - 编号
     * @param name - 角色名
     * @param managerId - 管理员编号
     */
    public Role(Integer id, String name, Integer managerId) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
    }

    /**
     * 默认无参构造函数
     */
    public Role() {
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
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


}
