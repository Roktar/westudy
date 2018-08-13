package bitcamp.java106.pms.service;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyFreeBoard;

public interface StudyFreeBoardService {
    int insert(StudyFreeBoard[] studyFreeBoards);
    List<StudyFreeBoard> selectList(int pageNo, int pageSize, String studyNo);
    int getCount(String studyNo);
    StudyFreeBoard listByOne(Map<String,Object> params);
    int update(StudyFreeBoard[] studyFreeBoards);
    void delete(int no);
    List<StudyFreeBoard> search(Map<String,Object> params);
    int getSearchCount(Map<String,Object> params);
}
