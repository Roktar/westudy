package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Review implements Serializable {
	 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String content;
    private int rating;
    private Date createdDate;
    private String category;
    private Member member;
    private Study study;
    
    
	public Study getStudy() {
		return study;
	}
	public void setStudy(Study study) {
		this.study = study;
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
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
    
    
    
    
    

    
    

}
