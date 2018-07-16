package main.java.bitcamp.java106.pms.domain;
import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private int no;
    private String name;
    private String email;    
    private String password;    
    @JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date birthDate;
    private char gender;
    private String location;
    private String photo;
    private String profile;
    
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
    @Override
    public String toString() {
        return "TestMember [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
                + ", birthDate=" + birthDate + ", gender=" + gender + ", location=" + location + ", photo=" + photo
                + ", profile=" + profile + "]";
    }
 
    
}
