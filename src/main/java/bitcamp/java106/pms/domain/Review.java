package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Review implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String content;
    private String category;
    private int rating;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
    
    
    private int studyNo;
    private int memNo;
    
    private Member member;
    private StudyInfo study;
    private int count;
 


   
   public StudyInfo getStudy() {
      return study;
   }
   public void setStudy(StudyInfo study) {
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
   
   public Member getMember() {
      return member;
   }
   public void setMember(Member member) {
      this.member = member;
   }
   public int getCount() {
      return count;
   }
   public void setCount(int count) {
      this.count = count;
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
   public String getCategory() {
      return category;
   }
   public void setCategory(String category) {
      this.category = category;
   }

    
    
    

    
    

}