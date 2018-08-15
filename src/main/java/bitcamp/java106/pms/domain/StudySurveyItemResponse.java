package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class StudySurveyItemResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int studyNo;
    private int memNo;
    private int surveyNo;
    private int itemNo;
    
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
    public int getSurveyNo() {
        return surveyNo;
    }
    public void setSurveyNo(int surveyNo) {
        this.surveyNo = surveyNo;
    }    
    public int getItemNo() {
        return itemNo;
    }
    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }
    
    @Override
    public String toString() {
        return "StudySurveyItemResponse [studyNo=" + studyNo + ", memNo=" + memNo + ", surveyNo=" + surveyNo
                + ", itemNo=" + itemNo + "]";
    }
}
