package love.drose.gms.models;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created by lovedrose on 3/29/16.
 */
public class DeviceUuid {
    @Id
    @Column(name = "Id")
    private Integer id;

    private Integer userId;

    private String uuid;

    public DeviceUuid(Integer id, Integer userId, String uuid) {
        this.id = id;
        this.userId = userId;
        this.uuid = uuid;
    }

    public DeviceUuid() {
    }

    @Override
    public String toString() {
        return "DeviecUuid{" +
                "id=" + id +
                ", userId=" + userId +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
