package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TestBase implements Serializable {
    private static final long serialVersionUID = 1L;
    private int no;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date startDate;
    private Time time;
    private String place;
    private String topic;
    List<TestAdditional> schedules;
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public List<TestAdditional> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<TestAdditional> schedules) {
        this.schedules = schedules;
    }
    @Override
    public String toString() {
        return "TestBase [no=" + no + ", title=" + title + ", startDate=" + startDate + ", time=" + time + ", place="
                + place + ", topic=" + topic + ", schedules=" + schedules + "]";
    }
}
