package love.drose.gms.models;

import java.util.Date;

/**
 * Created by lovedrose on 6/15/16.
 */
public class Salary {

    private Integer id;
    private String staffNo;
    private String staffName;
    private Double realSalary;
    private Double shouldSalary;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Double getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(Double realSalary) {
        this.realSalary = realSalary;
    }

    public Double getShouldSalary() {
        return shouldSalary;
    }

    public void setShouldSalary(Double shouldSalary) {
        this.shouldSalary = shouldSalary;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", staffNo='" + staffNo + '\'' +
                ", staffName='" + staffName + '\'' +
                ", realSalary=" + realSalary +
                ", shouldSalary=" + shouldSalary +
                ", createTime=" + createTime +
                '}';
    }

    public Salary() {
    }

    public Salary(Integer id, String staffNo, String staffName, Double realSalary, Double shouldSalary, Date createTime) {

        this.id = id;
        this.staffNo = staffNo;
        this.staffName = staffName;
        this.realSalary = realSalary;
        this.shouldSalary = shouldSalary;
        this.createTime = createTime;
    }
}
