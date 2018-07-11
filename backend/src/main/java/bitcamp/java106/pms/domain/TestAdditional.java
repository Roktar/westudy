package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Time;

public class TestAdditional implements Serializable {
    private static final long serialVersionUID = 1L;
    private int no;
    private Time startTime;
    private Time endTime;
    private String content;
       
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TestAdditional [no=" + no + ", startTime=" + startTime + ", endTime=" + endTime + ", content=" + content
                + "]";
    }
}
