package bitcamp.java106.pms.web.json;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.java106.pms.domain.TestSurvey;
import bitcamp.java106.pms.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class TestSurveyController {
    
    SurveyService surveyService;
    
    public TestSurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    
  
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(TestSurvey testSurvey) throws Exception{
    	surveyService.add(testSurvey);
    }
    
    
    @RequestMapping("{no}")
    public TestSurvey view(@PathVariable int no) throws Exception {
        return surveyService.get(no);
    }
}
