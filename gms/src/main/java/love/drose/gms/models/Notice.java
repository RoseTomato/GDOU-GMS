package love.drose.gms.models;

/**
 * Created by lovedrose on 6/16/16.
 */
public class Notice {
    private Integer id;
    private String description;
    private String dateStr;
    private String state;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateStr='" + dateStr + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Notice() {

    }

    public Notice(Integer id, String description, String dateStr, String state) {

        this.id = id;
        this.description = description;
        this.dateStr = dateStr;
        this.state = state;
    }
}
