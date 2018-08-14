package bitcamp.java106.pms.service;

import java.util.List;

import bitcamp.java106.pms.domain.StudySurvey;

public interface StudySurveyService {
    Object add(String[] params, int studyNo);
    List<StudySurvey> list(int no);
    Object vote(int studyNo, int memNo, int surveyNo, int itemNo);
    Object vote(String[] params);
}
