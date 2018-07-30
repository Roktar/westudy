package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class TestSurvey implements Serializable {
	 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String title;
    private String state;
    
    
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
   
    
    

}
