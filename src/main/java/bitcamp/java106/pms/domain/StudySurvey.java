package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudySurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date startDate;    
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date endDate;
    private int answerNum;
    private int studyNo;
    private List<StudySurveyItem> items;
    private int voteCount;
    
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
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public int getAnswerNum() {
        return answerNum;
    }
    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }
    public int getStudyNo() {
        return studyNo;
    }
    public void setStudyNo(int studyNo) {
        this.studyNo = studyNo;
    }
    public List<StudySurveyItem> getItems() {
        return items;
    }
    public void setItems(List<StudySurveyItem> items) {
        this.items = items;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    
    @Override
    public String toString() {
        return "StudySurvey [no=" + no + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
                + ", answerNum=" + answerNum + ", studyNo=" + studyNo + ", items=" + items + ", voteCount=" + voteCount
                + "]";
    }
}
