package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Photo implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String photo;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date createdDate;
    private int memNo;
    private int studyNo;
    
    
    
    @Override
    public String toString() {
        return "Photo [no=" + no + ", photo=" + photo + ", title=" + title + ", createdDate=" + createdDate + ", memNo="
                + memNo + ", studyNo=" + studyNo + "]";
    }

    public int getMemNo() {
        return memNo;
    }

    public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public int getStudyNo() {
        return studyNo;
    }

    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
      
}