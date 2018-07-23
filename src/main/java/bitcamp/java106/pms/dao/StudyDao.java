package bitcamp.java106.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.java106.pms.domain.Study;

public interface StudyDao {
    
    int delete(int no);    
    List<Study> selectList(Map<String,Object> params);
    int insert(Study study);
    int update(Study study);
    Study selectOne(int no);
    Study selectlimitOne();
}