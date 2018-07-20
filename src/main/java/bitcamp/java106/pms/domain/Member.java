package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Member implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String email;
    private String password;
    private String profile;
    private String introduce;
    private String name;
    private Date birthday;
    private String tel;
    private char gender;
    private String interestedPlace;
    private List<InterestField> interests;
    
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
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
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getInterestedPlace() {
        return interestedPlace;
    }
    public void setInterestedPlace(String interestedPlace) {
        this.interestedPlace = interestedPlace;
    }
    public List<InterestField> getInterests() {
        return interests;
    }
    public void setInterests(List<InterestField> interests) {
        this.interests = interests;
    }
    @Override
    public String toString() {
        return "Member [no=" + no + ", email=" + email + ", password=" + password + ", profile=" + profile
                + ", introduce=" + introduce + ", name=" + name + ", birthday=" + birthday + ", tel=" + tel
                + ", gender=" + gender + ", interestedPlace=" + interestedPlace + ", interests=" + interests + "]";
    }
}
