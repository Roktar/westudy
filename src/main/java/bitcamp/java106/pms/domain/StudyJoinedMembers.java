package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.util.List;

public class StudyJoinedMembers implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private Study study;
    private List<Member> members;
    private int grade;
    @Override
    public String toString() {
        return "StudyJoinedMember [study=" + study + ", members=" + members + ", grade=" + grade + "]";
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
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
