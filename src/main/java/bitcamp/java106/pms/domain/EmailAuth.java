package bitcamp.java106.pms.domain;

public class EmailAuth {
    private int no;
    private String authCode;
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getAuthCode() {
        return authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    @Override
    public String toString() {
        return "EmailAuth [no=" + no + ", authCode=" + authCode + "]";
    }
}
