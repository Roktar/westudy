package bitcamp.java106.pms.dao;

import bitcamp.java106.pms.domain.TestSurvey;

public interface SurveyDao {
    TestSurvey get(int no);
    void add(TestSurvey testSurvey);
   
}
