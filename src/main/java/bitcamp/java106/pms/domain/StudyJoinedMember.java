package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyJoinedMember implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int studyNo;
    private int memNo;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date registedDate;
    private int grade;
    
	private Member member;
	
	public Date getRegistedDate() {
		return registedDate;
	}
	public void setRegistedDate(Date registedDate) {
		this.registedDate = registedDate;
	}
	public int getStudyNo() {
		return studyNo;
	}
	public void setStudyNo(int studyNo) {
		this.studyNo = studyNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
