package bitcamp.java106.pms.service.impl;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.SurveyDao;
import bitcamp.java106.pms.domain.TestSurvey;
import bitcamp.java106.pms.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{
    SurveyDao surveyDao;
    
    public SurveyServiceImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

	@Override
	public void add(TestSurvey testSurvey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TestSurvey get(int no) {
		// TODO Auto-generated method stub
		return null;
	}
    
   
}
