package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lovedrose on 1/16/16.
 */
public class Equipment {

    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 器材名.
     */
    private String name;

    /**
     * 描述.
     */
    private String description;

    /**
     * 租金.
     */
    private Double fee;

    /**
     * 状态.
     */
    private String state;

    /**
     * 图片
     */
    private String image;

    /**
     * `
     * 使用类型.
     */
    private String useType;

    /**
     * 二级分类id
     */
    private Integer categoryId;

    /**
     * 该类型器材总数
     */
    private Integer total;

    /**
     * 该类型器材剩余数目
     */
    private Integer currentNumber;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fee=" + fee +
                ", state='" + state + '\'' +
                ", image='" + image + '\'' +
                ", useType='" + useType + '\'' +
                ", categoryId=" + categoryId +
                ", total=" + total +
                ", currentNumber=" + currentNumber +
                '}';
    }

    public Equipment(Integer id, String name, String description, Double fee, String state, String image, String useType, Integer categoryId, Integer total, Integer currentNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.state = state;
        this.image = image;
        this.useType = useType;
        this.categoryId = categoryId;
        this.total = total;
        this.currentNumber = currentNumber;
    }

    public Equipment() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }

}