package bitcamp.java106.pms.dao;

import java.util.Map;

import bitcamp.java106.pms.domain.StudySurveyItemResponse;

public interface StudySurveyItemResponseDao {
    int insert(Map<String, Object> params);
    int selectOneVoteCount(Map<String, Object> params);
    int selectListVoteCount(Map<String, Object> params);
    int isVoted(Map<String, Object> params);
    void insertCheckbox(StudySurveyItemResponse resp);
}
