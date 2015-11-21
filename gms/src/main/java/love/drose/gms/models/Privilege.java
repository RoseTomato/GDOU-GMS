package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by Administrator on 2015/11/20.
 */
public class Privilege {
    /**
     * 编号
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 角色id
     */
    private Integer roleId;

    public Privilege(Integer id, String name, Integer roleId) {
        this.id = id;
        this.name = name;
        this.roleId = roleId;
    }

    public Privilege() {
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
