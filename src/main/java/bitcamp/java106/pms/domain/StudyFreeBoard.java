package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyFreeBoard implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String title;
    private String file;
    private String originFile;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date createdDate;
    private int memNo;
    private int studyNo;
    private Member member;

    @Override
    public String toString() {
        return "StudyFreeBoard [no=" + no + ", title=" + title + ", file=" + file + ", originFile=" + originFile
                + ", content=" + content + ", createdDate=" + createdDate + ", memNo=" + memNo + ", studyNo=" + studyNo
                + ", member=" + member + "]";
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    public String getOriginFile() {
        return originFile;
    }
    
    public void setOriginFile(String originFile) {
        this.originFile = originFile;
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
}