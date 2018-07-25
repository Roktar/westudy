package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.util.List;

public class StudySurveyResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Study study;
    private List<Member> members;
    
    public Study getStudy() {
        return study;
    }
    public void setStudy(Study study) {
        this.study = study;
    }
    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    
}
