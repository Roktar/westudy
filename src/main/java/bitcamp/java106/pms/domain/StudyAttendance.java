package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyAttendance implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int no;
    private StudySchedule studySchedule;
    private Study study;
    private List<Member> members;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date time;
    
    public int getNo() {
        return no;
    }


    public void setNo(int no) {
        this.no = no;
    }


    public StudySchedule getSchedule() {
        return studySchedule;
    }


    public void setSchedule(StudySchedule studySchedule) {
        this.studySchedule = studySchedule;
    }


    public Study getStudy() {
        return study;
    }


    public void setStudy(Study study) {
        this.study = study;
    }


    public List<Member> getMembers() {
        return members;
    }


    public void setMembers(List<Member> members) {
        this.members = members;
    }


    public Date getTime() {
        return time;
    }


    public void setTime(Date time) {
        this.time = time;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    @Override
    public String toString() {
        return "Attendance [no=" + no + ", members=" + members + ", time=" + time + "]";
    }
    
    
}
