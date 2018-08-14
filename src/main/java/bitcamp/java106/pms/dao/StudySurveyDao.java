package bitcamp.java106.pms.dao;

import java.util.List;

import bitcamp.java106.pms.domain.StudySurvey;

public interface StudySurveyDao {
    int insert(StudySurvey survey);
    List<StudySurvey> selectList(int no);

}
