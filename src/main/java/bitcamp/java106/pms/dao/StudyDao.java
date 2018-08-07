package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.Study;

public interface StudyDao {
    int delete(int no);
    List<Study> selectList();
    int insert(Study study);
    int update(Study study);
    Study selectOne(int no);
}
