package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.util.List;

public class Member implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String email;
    private String password;
    private String profile;
    private String introduce;
    private String name;
    private String photo;
    private String tel;
    private String authCode;
    private List<InterestField> interests;
    private String city;
    private String county;
    
	@Override
	public String toString() {
		return "Member [no=" + no + ", email=" + email + ", password=" + password + ", profile=" + profile
				+ ", introduce=" + introduce + ", name=" + name + ", photo=" + photo + ", tel=" + tel + ", authCode="
				+ authCode + ", interests=" + interests + ", city=" + city + ", county=" + county + "]";
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public List<InterestField> getInterests() {
		return interests;
	}
	public void setInterests(List<InterestField> interests) {
		this.interests = interests;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
    
}
