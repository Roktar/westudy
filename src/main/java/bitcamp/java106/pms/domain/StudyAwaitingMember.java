package bitcamp.java106.pms.domain;

import java.io.Serializable;


import java.sql.Date;
import bitcamp.java106.pms.domain.StudyInfo;
import bitcamp.java106.pms.domain.Member;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyAwaitingMember implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int studyNo;
    private int memNo;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date requestDate;
    private int response;
    
	private Member member;
	private StudyInfo study;
	private StudyJoinedMember studyJoinedMember;
	
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public StudyInfo getStudy() {
		return study;
	}
	public void setStudy(StudyInfo study) {
		this.study = study;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public int getResponse() {
		return response;
	}
	public void setResponse(int response) {
		this.response = response;
	}
	public StudyJoinedMember getStudyJoinedMember() {
		return studyJoinedMember;
	}
	public void setStudyJoinedMember(StudyJoinedMember studyJoinedMember) {
		this.studyJoinedMember = studyJoinedMember;
	}
}
