package bitcamp.java106.pms.domain;

import java.io.Serializable;

public class StudyBoardFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int no;
    private StudyBoard boardNo;
    private String originFileName;
    private String saveFileName;
    private String filePath;
    private int fileSize;
    @Override
    public String toString() {
        return "StudyBoardFiles [no=" + no + ", boardNo=" + boardNo + ", originFileName=" + originFileName
                + ", saveFileName=" + saveFileName + ", filePath=" + filePath + ", fileSize=" + fileSize + "]";
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
    public String getOriginFileName() {
        return originFileName;
    }
    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }
    public String getSaveFileName() {
        return saveFileName;
    }
    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public int getFileSize() {
        return fileSize;
    }
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    
    
}
