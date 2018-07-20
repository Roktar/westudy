package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

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
    public TestBase setNo(int no) {
        this.no = no;
        return this;
    }
    public String getTitle() {
        return title;
    }
    public TestBase setTitle(String title) {
        this.title = title;
        return this;
    }
    public Date getStartDate() {
        return startDate;
    }
    public TestBase setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }
    public Time getTime() {
        return time;
    }
    public TestBase setTime(Time time) {
        this.time = time;
        return this;
    }
    public String getPlace() {
        return place;
    }
    public TestBase setPlace(String place) {
        this.place = place;
        return this;
    }
    public String getTopic() {
        return topic;
    }
    public TestBase setTopic(String topic) {
        this.topic = topic;
        return this;
    }
    public List<TestAdditional> getSchedules() {
        return schedules;
    }
    public TestBase setSchedules(List<TestAdditional> schedules) {
        this.schedules = schedules;
        return this;
    }
    @Override
    public String toString() {
        return "TestBase [no=" + no + ", title=" + title + ", startDate=" + startDate + ", time=" + time + ", place="
                + place + ", topic=" + topic + ", schedules=" + schedules + "]";
    }
}
