package main.java.bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Member implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private int no;
    private String email;
    private String password;
    private String  profilePicture;
    private String introduction;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date birth;
    private String telephone;
    private char gender;
    private String interestedLocation;
    
    @Override
    public String toString() {
        return "Member [no=" + no + ", email=" + email + ", password=" + password + ", profilePicture=" + profilePicture
                + ", introduction=" + introduction + ", name=" + name + ", birth=" + birth + ", telephone=" + telephone
                + ", gender=" + gender + ", interestedLocation=" + interestedLocation + "]";
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
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getInterestedLocation() {
        return interestedLocation;
    }
    public void setInterestedLocation(String interestedLocation) {
        this.interestedLocation = interestedLocation;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
