package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberMessage implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String content;
    private Member sender;
    private Member receiver;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")    
    private Date createdDate;
    @Override
    public String toString() {
        return "MemberMessage [no=" + no + ", content=" + content + ", sender=" + sender + ", receiver=" + receiver
                + ", createdDate=" + createdDate + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Member getSender() {
        return sender;
    }
    public void setSender(Member sender) {
        this.sender = sender;
    }
    public Member getReceiver() {
        return receiver;
    }
    public void setReceiver(Member receiver) {
        this.receiver = receiver;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    
    
}
