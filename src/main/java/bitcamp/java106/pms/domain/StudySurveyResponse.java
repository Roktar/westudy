package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.util.List;

public class StudySurveyResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Study study;
    private List<Member> members;
    
}
