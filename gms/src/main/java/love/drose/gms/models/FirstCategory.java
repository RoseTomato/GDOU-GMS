package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 一级分类
 * Created by lovedrose on 2015/12/10.
 */
public class FirstCategory {
    @Id
    @Column(name = "Id")
    private Integer id;

    private String name;

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

    @Override
    public String toString() {
        return "FirstCategory{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public FirstCategory() {
    }

    public FirstCategory(Integer id, String name) {

        this.id = id;
        this.name = name;
    }
}
