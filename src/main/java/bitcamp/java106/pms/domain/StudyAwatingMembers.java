package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyAwatingMembers implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private List<Member> members;
    private Study study;
    private String response;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date requestDate;
    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    @Override
    public String toString() {
        return "StudyAwatingMembers [members=" + members + ", study=" + study + ", response=" + response
                + ", requestDate=" + requestDate + "]";
    }
    
}
