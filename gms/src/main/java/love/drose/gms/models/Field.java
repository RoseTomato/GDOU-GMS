package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 场地模型
 * Created by lovedrose on 2015/11/27.
 */
public class Field {
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 场地名.
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

    /**`
     * 使用类型.
     */
    private String useType;

    /**
     * 可容纳人数
     */
    private Integer galleryful;

    /**
     * 二级分类id
     */
    private Integer categoryId;

    /**
     * 目前使用人数.
     */
    private Integer currentNumber;


    public Field() {
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fee=" + fee +
                ", state='" + state + '\'' +
                ", image='" + image + '\'' +
                ", useType='" + useType + '\'' +
                ", galleryful=" + galleryful +
                ", categoryId=" + categoryId +
                ", currentNumber=" + currentNumber +
                '}';
    }

    public Field(Integer id, String name, String description, Double fee, String state, String image, String useType, Integer galleryful, Integer categoryId, Integer currentNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.state = state;
        this.image = image;
        this.useType = useType;
        this.galleryful = galleryful;
        this.categoryId = categoryId;
        this.currentNumber = currentNumber;
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

    public Integer getGalleryful() {
        return galleryful;
    }

    public void setGalleryful(Integer galleryful) {
        this.galleryful = galleryful;
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

    public Integer getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(Integer currentNumber) {
        this.currentNumber = currentNumber;
    }
}
