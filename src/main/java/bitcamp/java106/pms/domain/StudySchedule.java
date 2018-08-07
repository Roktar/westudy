package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

public class StudySchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int no;
    private int refStudyNo;
    private String placeAddress;
    private String placeDetail;
    private double latitude;
    private double longitude;
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date StartDate;
    private Time time;
    private List<StudyScheduleDetail> schedules;
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getStartDate() {
        return StartDate;
    }
    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }
    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }
    public List<StudyScheduleDetail> getSchedules() {
        return schedules;
    }
    public void setSchedules(List<StudyScheduleDetail> schedules) {
        this.schedules = schedules;
    }
    public int getRefStudyNo() {
        return refStudyNo;
    }
    public void setRefStudyNo(int refStudyNo) {
        this.refStudyNo = refStudyNo;
    }
    public String getPlaceDetail() {
        return placeDetail;
    }
    public void setPlaceDetail(String placeDetail) {
        this.placeDetail = placeDetail;
    }
    public String getPlaceAddress() {
        return placeAddress;
    }
    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }
    @Override
    public String toString() {
        return "StudySchedule [no=" + no + ", refStudyNo=" + refStudyNo + ", placeAddress=" + placeAddress
                + ", placeDetail=" + placeDetail + ", latitude=" + latitude + ", longitude=" + longitude + ", title="
                + title + ", content=" + content + ", StartDate=" + StartDate + ", time=" + time + ", schedules="
                + schedules + "]";
    }
}
