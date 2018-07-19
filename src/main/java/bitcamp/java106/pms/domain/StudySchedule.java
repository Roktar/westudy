package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudySchedule implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int no;
    private Study study;
    private String place;
    private double lattitude;
    private double longitude;
    private String title;
    private String topic;
    private String detail;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date startTime;
    @Override
    public String toString() {
        return "StudySchedule [no=" + no + ", place=" + place + ", lattitude=" + lattitude + ", longitude=" + longitude
                + ", title=" + title + ", topic=" + topic + ", detail=" + detail + ", startTime=" + startTime + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public double getLattitude() {
        return lattitude;
    }
    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
