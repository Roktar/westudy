package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.util.List;

public class StudySurveyItem implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private int surveyNo;
    private String itemName;
    
    private int responseCount;
    
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getSurveyNo() {
        return surveyNo;
    }

    public void setSurveyNo(int surveyNo) {
        this.surveyNo = surveyNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(int responseCount) {
        this.responseCount = responseCount;
    }

    @Override
    public String toString() {
        return "StudySurveyItem [no=" + no + ", surveyNo=" + surveyNo + ", itemName=" + itemName + ", responseCount="
                + responseCount + "]";
    }
}