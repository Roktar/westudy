package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.StudySurveyItem;

public interface StudySurveyItemDao {
    int insert(StudySurveyItem item);
    List<StudySurveyItem> selectList(int no);
    
}
