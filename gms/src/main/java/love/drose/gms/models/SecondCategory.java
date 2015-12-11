package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 二级分类
 * Created by lovedrose on 2015/12/10.
 */
public class SecondCategory {

    @Id
    @Column(name = "Id")
    private Integer id;

    private String name;

    /**
     * 一级分类id
     */
    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SecondCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }

    public SecondCategory() {
    }

    public SecondCategory(Integer id, String name, Integer parentId) {

        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }
}
