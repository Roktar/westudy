package main.java.bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    private int attendanceNo;
    private int scheduleNo;
    private int studyNo;
    private int memberNo;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")    
    private Date attendanceTime;
    public int getAttendanceNo() {
        return attendanceNo;
    }
    public void setAttendanceNo(int attendanceNo) {
        this.attendanceNo = attendanceNo;
    }
    public int getScheduleNo() {
        return scheduleNo;
    }
    public void setScheduleNo(int scheduleNo) {
        this.scheduleNo = scheduleNo;
    }
    public int getStudyNo() {
        return studyNo;
    }
    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }
    public int getMemberNo() {
        return memberNo;
    }
    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }
    public Date getAttendanceTime() {
        return attendanceTime;
    }
    public void setAttendanceTime(Date attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "StudyAttendance [attendanceNo=" + attendanceNo + ", scheduleNo=" + scheduleNo + ", studyNo=" + studyNo
                + ", memberNo=" + memberNo + ", attendanceTime=" + attendanceTime + "]";
    }
    
    
    
}
