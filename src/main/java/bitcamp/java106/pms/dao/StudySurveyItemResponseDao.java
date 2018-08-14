package bitcamp.java106.pms.dao;

import java.util.Map;

public interface StudySurveyItemResponseDao {
    int insert(Map<String, Object> params);
    int selectOneVoteCount(Map<String, Object> params);
    int selectListVoteCount(Map<String, Object> params);
    int isVoted(Map<String, Object> params);
}
