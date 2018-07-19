package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyReview implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private String content;
    private int rating;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")    
    private Date createdDate;
    private String category;
    private Study study;
    private Member member;
    @Override
    public String toString() {
        return "StudyReview [no=" + no + ", content=" + content + ", rating=" + rating + ", createdDate=" + createdDate
                + ", category=" + category + ", study=" + study + ", member=" + member + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
