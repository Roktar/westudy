package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudySurveyDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private StudySurvey survey;
    private String item;
    
    @Override
    public String toString() {
        return "StudySurveyDetail [no=" + no + ", survey=" + survey + ", item=" + item + "]";
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public StudySurvey getSurvey() {
        return survey;
    }

    public void setSurvey(StudySurvey survey) {
        this.survey = survey;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
    
}
