package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyBoard implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private Study study;
    private Member member;
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date createdDate;
    private int maxPeople;
    private int type;
    @Override
    public String toString() {
        return "StudyBoard [no=" + no + ", study=" + study + ", member=" + member + ", title=" + title + ", content="
                + content + ", createdDate=" + createdDate + ", maxPeople=" + maxPeople + ", type=" + type + "]";
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
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
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
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getMaxPeople() {
        return maxPeople;
    }
    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
