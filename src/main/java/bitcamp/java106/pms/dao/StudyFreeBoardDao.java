package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyFreeBoard;

public interface StudyFreeBoardDao {
    int insert(StudyFreeBoard studyFreeBoard);
    List<StudyFreeBoard> selectList(Map<String, Object> params);
    int getCount(String studyNo);
    StudyFreeBoard listByOne(Map<String,Object> params);
    void update(StudyFreeBoard studyFreeBoard);
    void delete(int no);
    List<StudyFreeBoard> search(Map<String,Object> params);
    int getSearchCount(Map<String,Object> params);
}
