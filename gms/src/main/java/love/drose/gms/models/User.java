package love.drose.gms.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户模型.
 * Created by lovedrose on 2015/11/25.
 */
public class User {

    /**
     * 编号
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 学号
     */
    private String sno;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码.
     */
    private String password;

    /**
     * 姓名.
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄.
     */
    private Integer age;

    /**
     * 生日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 状态
     */
    private String state;

    /**
     * 头像
     */
    private String headImage;


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", state='" + state + '\'' +
                ", headImage='" + headImage + '\'' +
                '}';
    }

    public User(Integer id, String sno, String username, String password, String name, String gender, Integer age, Date birthday, String state, String headImage) {

        this.id = id;
        this.sno = sno;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
        this.state = state;
        this.headImage = headImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
}
