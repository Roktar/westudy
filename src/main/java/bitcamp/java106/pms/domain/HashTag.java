package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class HashTag implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String hashtag;
    
    @Override
    public String toString() {
        return "HashTag [no=" + no + ", hashtag=" + hashtag + "]";
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
    
}
