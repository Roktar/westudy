package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class StudySurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private String title;
    private String status;
    private Study study;
    @Override
    public String toString() {
        return "StudySurvey [no=" + no + ", title=" + title + ", status=" + status + ", study=" + study + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
