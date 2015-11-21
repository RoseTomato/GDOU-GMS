package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 管理员模型
 * Created by lovedrose on 2015/11/19.
 */
public class Manager {
    /**
     * 管理员编号
     */
    @Id
    @Column(name = "Id")
    private Integer id;

    /**
     * 管理员账号
     */
    private String username;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 管理员真实姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 联系地址
     */
    private String address;

    public Manager() {
    }

    public Manager(Integer id, String username, String password, String name, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /** ====================================  getter and setter ==================================== */
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
