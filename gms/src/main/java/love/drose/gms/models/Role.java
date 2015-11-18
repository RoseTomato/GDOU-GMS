package love.drose.gms.models;

/**
 * 角色模型
 * Created by lovedrose on 2015/11/18.
 */
public class Role {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 角色名
     */
    private String name;

    /**
     * 构造函数
     * @param id - 编号
     * @param name - 角色名
     */
    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 默认无参构造函数
     */
    public Role() {
    }

    /**
     * 重写toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}
