package bitcamp.java106.pms.service.impl;

import java.net.URLDecoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import bitcamp.java106.pms.dao.StudySurveyDao;
import bitcamp.java106.pms.dao.StudySurveyItemDao;
import bitcamp.java106.pms.dao.StudySurveyItemResponseDao;
import bitcamp.java106.pms.domain.StudySurvey;
import bitcamp.java106.pms.domain.StudySurveyItem;
import bitcamp.java106.pms.service.StudySurveyService;

@Service
public class StudySurveyServiceImpl implements StudySurveyService {

    StudySurveyDao surveyDao;
    StudySurveyItemDao itemDao;
    StudySurveyItemResponseDao resDao;
    
    public StudySurveyServiceImpl(StudySurveyDao surveyDao, StudySurveyItemDao itemDao, StudySurveyItemResponseDao resDao) {
        this.surveyDao = surveyDao;
        this.itemDao = itemDao;
        this.resDao = resDao;
    }
    
    @Override
    public Object add(String[] params, int no) {
        StudySurvey survey = new StudySurvey();
        List<String> items = new ArrayList<>();
        
        for(String p : params) {
            try {
                String[] kv = p.split("=");
                
                if(kv[0].contains("items")) {
                    items.add(URLDecoder.decode(kv[1], "UTF-8"));
                    continue;
                }
                
                switch(kv[0]) {
                    case "title" :
                        survey.setTitle(URLDecoder.decode(kv[1], "UTF-8"));
                        break;
                    case "startDate" :
                        survey.setStartDate( Date.valueOf(kv[1]) );
                        break;
                    case "endDate" :
                        survey.setEndDate( Date.valueOf(kv[1]) );
                        break;
                    case "answerNum" :
                        survey.setAnswerNum( Integer.parseInt(kv[1]) );
                        break;
                    case "studyNo" :
                        survey.setStudyNo( Integer.parseInt(kv[1]) );
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "fail";
            }
        }
        
        survey.setStudyNo(no);
        surveyDao.insert(survey);
        int surveyNo = survey.getNo();
        try {
            for(String s : items) {
                StudySurveyItem item = new StudySurveyItem();
                item.setSurveyNo(surveyNo);
                item.setItemName(s);
                itemDao.insert(item);
            }
        } catch(Exception e) {
            return "fail";
        }
        
        return "success";
    }

    @Override
    public List<StudySurvey> list(int no) {
        System.out.println("no : " + no);
        List<StudySurvey> list = surveyDao.selectList(no);
        
        for(StudySurvey sv : list) {
            sv.setItems(itemDao.selectList(sv.getNo()));
            Map<String, Object> all_params = new HashMap<>();
            all_params.put("studyNo", no);
            all_params.put("surveyNo", sv.getNo());
            sv.setVoteCount(resDao.selectListVoteCount(all_params));
            
            for(StudySurveyItem si : sv.getItems()) {
                Map<String, Object> params = new HashMap<>();
                params.put("studyNo", no);
                params.put("surveyNo", sv.getNo());
                params.put("itemNo", si.getNo());
                si.setResponseCount( resDao.selectOneVoteCount(params) );
            }
        }
        return list;
    }

    @Override
    public Object vote(int studyNo, int memNo, int surveyNo, int itemNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("studyNo", studyNo);
        params.put("memNo", memNo);
        params.put("surveyNo", surveyNo);
        params.put("itemNo", itemNo);
        
        try {
            if(resDao.isVoted(params) > 0)
                return "voted";
            
            return ( resDao.insert(params) > 0 ? "success" : "fail" );
        } catch(Exception e) {
            return "fail";
        }
    }
}
