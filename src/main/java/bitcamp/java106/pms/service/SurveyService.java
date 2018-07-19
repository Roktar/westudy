package bitcamp.java106.pms.service;


import bitcamp.java106.pms.domain.TestSurvey;

public interface SurveyService {
    void add(TestSurvey testSurvey);
    TestSurvey get(int no);
    
  
}
