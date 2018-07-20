package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StudyBoardPictures implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private StudyBoard boardNo;
    private String originPictureName;
    private String savedPictureName;
    private String picturePath;
    @Override
    public String toString() {
        return "StudyPictures [no=" + no + ", boardNo=" + boardNo + ", originPictureName=" + originPictureName
                + ", savedPictureName=" + savedPictureName + ", picturePath=" + picturePath + "]";
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public StudyBoard getBoardNo() {
        return boardNo;
    }
    public void setBoardNo(StudyBoard boardNo) {
        this.boardNo = boardNo;
    }
    public String getOriginPictureName() {
        return originPictureName;
    }
    public void setOriginPictureName(String originPictureName) {
        this.originPictureName = originPictureName;
    }
    public String getSavedPictureName() {
        return savedPictureName;
    }
    public void setSavedPictureName(String savedPictureName) {
        this.savedPictureName = savedPictureName;
    }
    public String getPicturePath() {
        return picturePath;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    
}
