package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class InterestField implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String category;
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Override
    public String toString() {
        return "InterestField [no=" + no + ", category=" + category + "]";
    }
}
