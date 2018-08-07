package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.StudyInfo;

public interface StudyInfoDao {
    
    int delete(int no);    
    List<StudyInfo> selectList(Map<String,Object> params);
    List<StudyInfo> selectSearchList(Map<String,Object> searchs);
    int insert(StudyInfo studyInfo);
    int update(StudyInfo studyInfo);
    StudyInfo selectOne(int no);
    StudyInfo selectlimitOne();
}