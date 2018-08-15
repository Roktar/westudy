package bitcamp.java106.pms.dao;

import java.util.Map;

import bitcamp.java106.pms.domain.Member;

public interface MemberDao {
    Member selectOne(String id);  
    Member validation(Map<String, Object> params);
    void insert_basic(Member member);
    int count(Map<String, Object> params);
    Member selectOneSimpleCase(String id);
    int changePassword(Map<String, Object> params);
    int update(Member member);
    void createAuthKey(String email, String key);
    void userAuth(Map<String, Object> params);
	Member selectOneByNo(int user);
    int find(String id);
    int uploadPhoto(Map<String, Object> params);
    int uploadExcludePhoto(Map<String, Object> params);
    int checkId(String email);
    
    Member findMember(String id);
    
    // facebook, kakao
    Member findByEmail(String email);
    int add(Member member);
}
